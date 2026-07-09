package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Repository.JobRepository;
import CS340.PetPal.Entity.Job;

@Service
public class JobService {
    private final JobRepository serviceRepository;

    public JobService(JobRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Job> getAllServices() {
        return this.serviceRepository.findAll();
    }

    public Optional<Job> getServiceById(Long serviceId) {
        return this.serviceRepository.findById(serviceId);
    }

    public Job createService(Job provider) {
        return this.serviceRepository.save(provider);
    }

    public Job updateService(Long serviceId, Job service) {
        Job existingService = this.serviceRepository.findById(serviceId).orElse(null);
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
