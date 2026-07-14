package CS340.PetPal.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {
    private static final String PREFIX = "pbkdf2";
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 210_000;
    private static final int KEY_LENGTH_BITS = 256;
    private static final int SALT_LENGTH_BYTES = 16;

    private final SecureRandom secureRandom = new SecureRandom();

    public String hash(String password) {
        byte[] salt = new byte[SALT_LENGTH_BYTES];
        secureRandom.nextBytes(salt);
        byte[] derivedKey = deriveKey(password, salt, ITERATIONS);
        return String.join("$",
                PREFIX,
                Integer.toString(ITERATIONS),
                Base64.getEncoder().encodeToString(salt),
                Base64.getEncoder().encodeToString(derivedKey));
    }

    public boolean matches(String password, String storedPassword) {
        if (password == null || storedPassword == null) {
            return false;
        }

        if (!storedPassword.startsWith(PREFIX + "$")) {
            return MessageDigest.isEqual(
                    password.getBytes(java.nio.charset.StandardCharsets.UTF_8),
                    storedPassword.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }

        String[] parts = storedPassword.split("\\$");
        if (parts.length != 4) {
            return false;
        }

        try {
            int iterations = Integer.parseInt(parts[1]);
            byte[] salt = Base64.getDecoder().decode(parts[2]);
            byte[] expectedKey = Base64.getDecoder().decode(parts[3]);
            byte[] actualKey = deriveKey(password, salt, iterations);
            return MessageDigest.isEqual(expectedKey, actualKey);
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public boolean needsUpgrade(String storedPassword) {
        return storedPassword != null && !storedPassword.startsWith(PREFIX + "$");
    }

    private byte[] deriveKey(String password, byte[] salt, int iterations) {
        PBEKeySpec specification = new PBEKeySpec(password.toCharArray(), salt, iterations, KEY_LENGTH_BITS);
        try {
            return SecretKeyFactory.getInstance(ALGORITHM).generateSecret(specification).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new IllegalStateException("Password hashing is unavailable.", exception);
        } finally {
            specification.clearPassword();
        }
    }
}
