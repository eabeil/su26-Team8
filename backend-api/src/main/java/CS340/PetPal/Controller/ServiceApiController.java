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

import CS340.PetPal.Service.ServiceService;

@RestController
@RequestMapping("/api/services")
public class ServiceApiController {

    private final ServiceService serviceService;

    public ServiceApiController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // get services
    @GetMapping("/")
    public ResponseEntity<List<CS340.PetPal.Entity.Service>> getAllServices() {
        List<CS340.PetPal.Entity.Service> services = this.serviceService.getAllServices();
        if (services.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(services);
    }

    // get service
    @GetMapping("/{id}")
    public ResponseEntity<CS340.PetPal.Entity.Service> getServiceById(@PathVariable Long serviceId) {
        return this.serviceService.getServiceById(serviceId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // create service
    @PostMapping()
    public ResponseEntity<CS340.PetPal.Entity.Service> createService(@RequestBody CS340.PetPal.Entity.Service service) {
        CS340.PetPal.Entity.Service createdService = this.serviceService.createService(service);
        return ResponseEntity.created(null).body(createdService);
    }

    // update service
    @PutMapping("/{id}")
    public ResponseEntity<CS340.PetPal.Entity.Service> updateService(@PathVariable Long serviceId, @RequestBody CS340.PetPal.Entity.Service service) {
        try {
            CS340.PetPal.Entity.Service updatedService = this.serviceService.updateService(serviceId, service);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}