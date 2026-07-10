package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Repository.UpdateRepository;
import CS340.PetPal.Entity.Update;

@Service
public class UpdateService {
    private final UpdateRepository updateRepository;

    public UpdateService(UpdateRepository updateRepository) {
        this.updateRepository = updateRepository;
    }

    public List<Update> getAllUpdates() {
        return this.updateRepository.findAll();
    }

    public Optional<Update> getUpdateById(Long updateId) {
        return this.updateRepository.findById(updateId);
    }

    public Update createUpdate(Update update) {
        return this.updateRepository.save(update);
    }

    public Update updateUpdate(Long updateId, Update update) {
        Update existingUpdate = this.updateRepository.findById(updateId).orElse(null);
        if (existingUpdate == null) {
            throw new RuntimeException("Update not found with id: " + updateId);
        }
        if (!update.getName().isEmpty()) {
            existingUpdate.setName(update.getName());
        }
        if (update.getTime() != null) {
            existingUpdate.setTime(update.getTime());
        }
        if (!update.getDuration().isEmpty()) {
            existingUpdate.setDuration((update.getDuration()));
        }
        if (update.getPrice() != 0) {
            existingUpdate.setPrice(update.getPrice());
        }
        return this.updateRepository.save(existingUpdate);
    }
}
