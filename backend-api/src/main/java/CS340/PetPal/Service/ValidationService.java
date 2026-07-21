package CS340.PetPal.Service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private static final Pattern ASCII_PATTERN = Pattern.compile("^\\p{ASCII}*$");

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\(\\d{3}\\) \\d{3}-\\d{4}$");

    private static final Pattern URL_PATTERN = Pattern.compile(
            "^(https?://)?(www\\.)?[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+([/?#][^\\s]*)?$");

    private static final int MIN_AGE = 0;

    private static final int MAX_AGE = 130;

    private static final double MIN_PRICE = 0.0;

    private static final double MAX_PRICE = 1_000_000.0;

    public boolean getIsValidString(String string) {
        if (string.trim().isEmpty()) {
            return true;
        }
        return ValidationService.ASCII_PATTERN.matcher(string).matches();
    }

    public Boolean getIsValidAge(Integer age) {
        return age >= ValidationService.MIN_AGE && age <= ValidationService.MAX_AGE;
    }

    public boolean getIsValidEmail(String string) {
        if (!this.getIsValidString(string)) {
            return false;
        }
        return ValidationService.EMAIL_PATTERN.matcher(string).matches();
    }

    public boolean getIsValidPhoneNumber(String number) {
        if (!this.getIsValidString(number)) {
            return false;
        }
        return ValidationService.PHONE_PATTERN.matcher(number).matches();
    }

    public boolean getIsValidAddress(String name) {
        return this.getIsValidString((name));
    }

    public boolean getIsValidPrice(Double price) {
        if (price.isNaN()) {
            return false;
        }
        if (price.isInfinite()) {
            return false;
        }
        return price >= ValidationService.MIN_PRICE && price <= ValidationService.MAX_PRICE;
    }

    public boolean getIsValidUrl(String url) {
        if (!this.getIsValidString(url)) {
            return false;
        }
        return ValidationService.URL_PATTERN.matcher(url).matches();
    }
}
