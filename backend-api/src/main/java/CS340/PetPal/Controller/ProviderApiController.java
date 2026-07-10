package CS340.PetPal.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Service.ProviderService;
import CS340.PetPal.Entity.Job;
import CS340.PetPal.Entity.Provider;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Entity.Update;
import CS340.PetPal.Dto.CreateProviderDto;
import CS340.PetPal.Dto.UpdateProviderDto;

@RestController
@RequestMapping("/api/providers")
public class ProviderApiController {

    private final ProviderService providerService;

    public ProviderApiController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // get providers
    @GetMapping("/")
    public ResponseEntity<List<Provider>> getAllProviders() {
        try {
        List<Provider> providers = this.providerService.getAllProviders();
        return ResponseEntity.ok(providers);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get provider
    @GetMapping("/{id}/")
    public ResponseEntity<Provider> getProviderById(@PathVariable("id") Long providerId) {
        try {
        Provider provider = this.providerService.getProviderById(providerId);
        return ResponseEntity.ok(provider);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get provider jobs
    @GetMapping("/{id}/jobs/")
    public ResponseEntity<List<Job>> getProviderJobs(@PathVariable("id") Long providerId) {
        try {
        List<Job> jobs = this.providerService.getProviderJobs(providerId);
        return ResponseEntity.ok(jobs);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get provider updates
    @GetMapping("/{id}/updates/")
    public ResponseEntity<List<Update>> getProviderUpdates(@PathVariable("id") Long providerId) {
        try {
        List<Update> updates = this.providerService.getProviderUpdates(providerId);
        return ResponseEntity.ok(updates);
        } catch(ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get provider reviews
    @GetMapping("/{id}/reviews/")
    public ResponseEntity<List<Review>> getProviderReviews(@PathVariable("id") Long providerId) {
        try {
        List<Review> reviews = this.providerService.getProviderReviews(providerId);
        return ResponseEntity.ok(reviews);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // create provider
    @PostMapping("/")
    public ResponseEntity<Provider> createProvider(@RequestBody CreateProviderDto dto) {
        try {
        Provider provider = this.providerService.createProvider(dto);
        URI location = URI.create("/api/providers/" + provider.getId());
        return ResponseEntity.created(location).body(provider);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // update provider
    @PutMapping("/{id}/")
    public ResponseEntity<Provider> updateProvider(@PathVariable("id") Long providerId,
            @RequestBody UpdateProviderDto dto) {
        try {
            Provider updatedProvider = this.providerService.updateProvider(providerId, dto);
            return ResponseEntity.ok(updatedProvider);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // delete provider
    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deleteProvider(@PathVariable("id") Long providerId) {
        try {
        this.providerService.deleteProvider(providerId);
        return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
} 