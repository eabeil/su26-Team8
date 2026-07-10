package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Repository.ProviderRepository;
import CS340.PetPal.Repository.UpdateRepository;
import CS340.PetPal.Dto.CreateUpdateDto;
import CS340.PetPal.Dto.UpdateUpdateDto;
import CS340.PetPal.Entity.Update;
import CS340.PetPal.Entity.Provider;

@Service
public class UpdateService {
    private final UpdateRepository updateRepository;
    private final ProviderRepository providerRepository;

    public UpdateService(UpdateRepository updateRepository, ProviderRepository providerRepository) {
        this.updateRepository = updateRepository;
        this.providerRepository = providerRepository;
    }

    public List<Update> getAllUpdates() {
        return this.updateRepository.findAll();
    }

    public Optional<Update> getUpdateById(Long updateId) {
        return this.updateRepository.findById(updateId);
    }

    public Update createUpdate(CreateUpdateDto dto) {
        Optional<Provider> providerO = this.providerRepository.findById(dto.getProviderId());
        if (providerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no provider of id " + dto.getProviderId());
        }
        Provider provider = providerO.get();
        Update update = new Update(dto.getTitle(), dto.getTime(), dto.getDuration(), dto.getPrice(), provider);
        return this.updateRepository.save(update);
    }

    public Update updateUpdate(Long updateId, UpdateUpdateDto dto) {
        Optional<Update> existingUpdateO = this.updateRepository.findById(updateId);
        if (existingUpdateO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no update with id " + updateId);
        }
        Update existingUpdate = existingUpdateO.get();
        existingUpdate.setTitle(dto.getTitle());
        existingUpdate.setTime(dto.getTime());
        existingUpdate.setDuration(dto.getDuration());
        existingUpdate.setPrice(dto.getPrice());
        return this.updateRepository.save(existingUpdate);
    }

    public void deleteUpdate(Long updateId) {
        Optional<Update> updateO = this.updateRepository.findById(updateId);
        if (updateO.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no update with id " + updateId);
        }
        Update update = updateO.get();
        this.updateRepository.delete(update);
    }
}
