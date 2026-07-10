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
        Optional<Update> existingUpdateO = this.updateRepository.findById(updateId);
        if (existingUpdateO.isEmpty()) {
            throw new RuntimeException("Update not found with id: " + updateId);
        }
        Update existingUpdate = existingUpdateO.get();
        existingUpdate.setTitle(update.getTitle());
        existingUpdate.setTime(update.getTime());
        existingUpdate.setDuration((update.getDuration()));
        existingUpdate.setPrice(update.getPrice());
        return this.updateRepository.save(existingUpdate);
    }
}
