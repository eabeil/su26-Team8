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

import CS340.PetPal.Service.UpdateService;
import CS340.PetPal.Entity.Update;

@RestController
@RequestMapping("/api/updates")
public class UpdateApiController {

    private final UpdateService updateService;

    public UpdateApiController(UpdateService updateService) {
        this.updateService = updateService;
    }

    // get updates
    @GetMapping("/")
    public ResponseEntity<List<Update>> getAllUpdates() {
        List<Update> providers = this.updateService.getAllUpdates();
        if (providers.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(providers);
    }

    // get update
    @GetMapping("/{id}")
    public ResponseEntity<Update> getUpdateById(@PathVariable Long updateId) {
        return this.updateService.getUpdateById(updateId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // create update
    @PostMapping()
    public ResponseEntity<Update> createProvider(@RequestBody Update update) {
        Update createdUpdate = this.updateService.createUpdate(update);
        return ResponseEntity.created(null).body(createdUpdate);
    }

    // update update
    @PutMapping("/{id}")
    public ResponseEntity<Update> updateUpdate(@PathVariable Long providerId, @RequestBody Update update) {
        try {
            Update updatedUpdate = this.updateService.updateUpdate(providerId, update);
            return ResponseEntity.ok(updatedUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}