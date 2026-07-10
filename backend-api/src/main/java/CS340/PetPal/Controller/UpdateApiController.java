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

import CS340.PetPal.Service.UpdateService;
import CS340.PetPal.Dto.CreateUpdateDto;
import CS340.PetPal.Dto.UpdateUpdateDto;
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
        return ResponseEntity.ok(providers);
    }

    // get update
    @GetMapping("/{id}")
    public ResponseEntity<Update> getUpdateById(@PathVariable("id") Long updateId) {
        Optional<Update> updateO = this.updateService.getUpdateById(updateId);
        if (updateO.isPresent()) {
            Update update = updateO.get();
            return ResponseEntity.ok(update);
        }
        return ResponseEntity.notFound().build();
    }

    // create update
    @PostMapping("/")
    public ResponseEntity<Update> createUpdate(@RequestBody CreateUpdateDto dto) {
        Update createdUpdate = this.updateService.createUpdate(dto);
        URI location = URI.create("/api/updates/" + createdUpdate.getId());
        return ResponseEntity.created(location).body(createdUpdate);
    }

    // update update
    @PutMapping("/{id}")
    public ResponseEntity<Update> updateUpdate(@PathVariable("id") Long providerId, @RequestBody UpdateUpdateDto dto) {
        try {
            Update updatedUpdate = this.updateService.updateUpdate(providerId, dto);
            return ResponseEntity.ok(updatedUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // delete update
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUpdate(@PathVariable("id") Long updateId) {
        boolean deletedOk = this.updateService.deleteUpdate(updateId);
        if (!deletedOk) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}