package CS340.PetPal.Controller;

import java.net.URI;
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

import CS340.PetPal.Service.JobService;
import CS340.PetPal.Entity.Job;

@RestController
@RequestMapping("/api/services")
public class JobApiController {

    private final JobService serviceService;

    public JobApiController(JobService serviceService) {
        this.serviceService = serviceService;
    }

    // get services
    @GetMapping("/")
    public ResponseEntity<List<Job>> getAllServices() {
        List<Job> services = this.serviceService.getAllServices();
        if (services.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(services);
    }

    // get service
    @GetMapping("/{id}")
    public ResponseEntity<Job> getServiceById(@PathVariable("id") Long serviceId) {
        return this.serviceService.getServiceById(serviceId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // create service
    @PostMapping()
    public ResponseEntity<Job> createService(@RequestBody Job service) {
        Job createdService = this.serviceService.createService(service);
        URI location = URI.create("/api/services/" + createdService.getId());
        return ResponseEntity.created(location).body(createdService);
    }

    // update service
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateService(@PathVariable("id") Long serviceId, @RequestBody Job service) {
        try {
           Job updatedService = this.serviceService.updateService(serviceId, service);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}