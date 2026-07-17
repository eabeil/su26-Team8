package CS340.PetPal.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Repository.JobRepository;
import CS340.PetPal.Repository.ProviderRepository;
import CS340.PetPal.Repository.ReviewRepository;
import CS340.PetPal.Repository.UpdateRepository;
import CS340.PetPal.Entity.Job;
import CS340.PetPal.Entity.Provider;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Entity.Update;
import CS340.PetPal.Dto.ProviderCreateDto;
import CS340.PetPal.Dto.ProviderUpdateDto;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final JobRepository jobRepository;
    private final UpdateRepository updateRepository;
    private final ReviewRepository reviewRepository;

    public ProviderService(ProviderRepository providerRepository, JobRepository jobRepository,
            ReviewRepository reviewRepository, UpdateRepository updateRepository) {
        this.providerRepository = providerRepository;
        this.jobRepository = jobRepository;
        this.updateRepository = updateRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Provider> getAllProviders() {
        return this.providerRepository.findAll();
    }

    public Provider getProviderById(Long providerId) {
        Optional<Provider> providerO = this.providerRepository.findById(providerId);
        if (providerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no provider with id " + providerId + ".");
        }
        Provider provider = providerO.get();
        return provider;
    }

    public List<Job> getProviderJobs(Long providerId) {
        return this.jobRepository.findByProviderId(providerId);
    }

    public List<Update> getProviderUpdates(Long providerId) {
        return this.updateRepository.findByProviderId(providerId);
    }

    public List<Review> getProviderReviews(Long providerId) {
        return this.reviewRepository.findByProviderId(providerId);
    }

    public Provider createProvider(ProviderCreateDto dto) {
        if (this.providerRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "already provider with email");
        }
        Provider provider = new Provider(dto.getName(), dto.getDescription(), dto.getImageUrl(), dto.getAddress(),
                dto.getPhone(), dto.getEmail(), Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList());
        return this.providerRepository.save(provider);
    }

    public Provider updateProvider(Long providerId, ProviderUpdateDto dto) {
        Optional<Provider> providerO = this.providerRepository.findById(providerId);
        if (providerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no provider with id " + providerId + ".");
        }
        Provider provider = providerO.get();
        if (provider.getEmail() != dto.getEmail()) {
            if (this.providerRepository.existsByEmail(dto.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "already provider with email");
            }
        }
        provider.setName(dto.getName());
        provider.setDescription(dto.getDescription());
        provider.setImageUrl(dto.getImageUrl());
        provider.setAddress(dto.getAddress());
        provider.setPhone(dto.getPhone());
        provider.setEmail(dto.getEmail());
        return this.providerRepository.save(provider);
    }

    public void deleteProvider(Long providerId) {
        Optional<Provider> providerO = this.providerRepository.findById(providerId);
        if (providerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no provider with id " + providerId + ".");
        }
        Provider provider = providerO.get();
        this.providerRepository.delete(provider);
    }

    public boolean getProviderEmailAvaliable(String email) {
        return this.providerRepository.existsByEmail(email);
    }
}
