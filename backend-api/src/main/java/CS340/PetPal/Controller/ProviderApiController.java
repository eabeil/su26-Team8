package CS340.PetPal.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CS340.PetPal.Service.ProviderService;
import CS340.PetPal.Entity.Provider;
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
        List<Provider> providers = this.providerService.getAllProviders();
        return ResponseEntity.ok(providers);
    }

    // get provider
    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable("id") Long providerId) {
        Optional<Provider> providerO = this.providerService.getProviderById(providerId);
        if (providerO.isPresent()) {
            Provider provider = providerO.get();
            return ResponseEntity.ok(provider);
        }
        return ResponseEntity.notFound().build();
    }

    // create provider
    @PostMapping("/")
    public ResponseEntity<Provider> createProvider(@RequestBody CreateProviderDto dto) {
        Provider createdProvider = this.providerService.createProvider(dto);
        URI location = URI.create("/api/providers/" + createdProvider.getId());
        return ResponseEntity.created(location).body(createdProvider);
    }

    // update provider
    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable("id") Long providerId,
            @RequestBody UpdateProviderDto dto) {
        try {
            Provider updatedProvider = this.providerService.updateProvider(providerId, dto);
            return ResponseEntity.ok(updatedProvider);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // delete provider
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable("id") Long providerId) {
        this.providerService.deleteProvider(providerId);
        return ResponseEntity.noContent().build();
    }
}