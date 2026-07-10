package CS340.PetPal.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Repository.ProviderRepository;
import CS340.PetPal.Entity.Provider;
import CS340.PetPal.Dto.CreateProviderDto;
import CS340.PetPal.Dto.UpdateProviderDto;

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

    public Provider createProvider(CreateProviderDto dto) {
        Provider provider = new Provider(dto.getName(), dto.getDescription(), dto.getImageUrl(), dto.getAddress(),
                dto.getPhone(), dto.getEmail(), Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList());
        return this.providerRepository.save(provider);
    }

    public Provider updateProvider(Long providerId, UpdateProviderDto dto) {
        Optional<Provider> existingProviderO = this.providerRepository.findById(providerId);
        if (existingProviderO.isEmpty()) {
            throw new RuntimeException("Provider not found with id: " + providerId);
        }
        Provider existingProvider = existingProviderO.get();
        existingProvider.setName(dto.getName());
        existingProvider.setDescription(dto.getDescription());
        existingProvider.setImageUrl(dto.getImageUrl());
        existingProvider.setAddress(dto.getAddress());
        existingProvider.setPhone(dto.getPhone());
        existingProvider.setEmail(dto.getEmail());
        return this.providerRepository.save(existingProvider);
    }
}
