package CS340.PetPal.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CS340.PetPal.Service.ProviderService;
import CS340.PetPal.Entity.Provider;;

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
        if (providers.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(providers);
    }

    // get provider
    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long providerId) {
        return this.providerService.getProviderById(providerId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // create provider
    @PostMapping
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        Provider createdProvider = this.providerService.createProvider(provider);
        return ResponseEntity.created(null).body(createdProvider);
    }

    // update provider
    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long providerId, @RequestBody Provider provider) {
        try {
            Provider updatedProvider = this.providerService.updateProvider(providerId, provider);
            return ResponseEntity.ok(updatedProvider);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}