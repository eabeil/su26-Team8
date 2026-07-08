package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Repository.ProviderRepository;
import CS340.PetPal.Entity.Provider;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public List<Provider> getAllProviders() {
        return this.providerRepository.findAll();
    }

    public Optional<Provider> getProviderById(Long providerId) {
        return this.providerRepository.findById(providerId);
    }

    public Provider createProvider(Provider provider) {
        return this.providerRepository.save(provider);
    }

    public Provider updateProvider(Long providerId, Provider provider) {
        Provider existingProvider = this.providerRepository.findById(providerId).orElse(null);
        if (existingProvider == null) {
            throw new RuntimeException("Provider not found with id: " + providerId);
        }
        if (!provider.getName().isEmpty()) {
            existingProvider.setName(provider.getName());
        }
        if (!provider.getDescription().isEmpty()) {
            existingProvider.setDescription(provider.getDescription());
        }
        if (!provider.getImageUrl().isEmpty()) {
            existingProvider.setImageUrl(provider.getImageUrl());
        }
        if (!provider.getAddress().isEmpty()) {
            existingProvider.setAddress(provider.getAddress());
        }
        if (!provider.getPhone().isEmpty()) {
            existingProvider.setPhone(provider.getPhone());
        }
        if (!provider.getEmail().isEmpty()) {
            existingProvider.setEmail(provider.getEmail());
        }
        return this.providerRepository.save(existingProvider);
    }
}
