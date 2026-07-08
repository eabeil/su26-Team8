package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Repository.ServiceRepository;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<CS340.PetPal.Entity.Service> getAllServices() {
        return this.serviceRepository.findAll();
    }

    public Optional<CS340.PetPal.Entity.Service> getServiceById(Long serviceId) {
        return this.serviceRepository.findById(serviceId);
    }

    public CS340.PetPal.Entity.Service createService(CS340.PetPal.Entity.Service provider) {
        return this.serviceRepository.save(provider);
    }

    public CS340.PetPal.Entity.Service updateService(Long serviceId, CS340.PetPal.Entity.Service service) {
        CS340.PetPal.Entity.Service existingService = this.serviceRepository.findById(serviceId).orElse(null);
        if (existingService == null) {
            throw new RuntimeException("SErvice not found with id: " + serviceId);
        }
        if (!service.getName().isEmpty()) {
            existingService.setName(service.getName());
        }
        if (service.getTime() != null) {
            existingService.setTime(service.getTime());
        }
        if (!service.getDuration().isEmpty()) {
            existingService.setDuration(service.getDuration());
        }
        if (service.getPrice() != 0) {
            existingService.setPrice(service.getPrice());
        }
        return this.serviceRepository.save(existingService);
    }
}
