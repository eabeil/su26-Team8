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

import CS340.PetPal.Service.PetServiceService;
import CS340.PetPal.Entity.PetService;

@RestController
@RequestMapping("/api/services")
public class ServiceApiController {

    private final PetServiceService serviceService;

    public ServiceApiController(PetServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // get services
    @GetMapping("/")
    public ResponseEntity<List<PetService>> getAllServices() {
        List<PetService> services = this.serviceService.getAllServices();
        if (services.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(services);
    }

    // get service
    @GetMapping("/{id}")
    public ResponseEntity<PetService> getServiceById(@PathVariable Long serviceId) {
        return this.serviceService.getServiceById(serviceId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // create service
    @PostMapping()
    public ResponseEntity<PetService> createService(@RequestBody PetService service) {
        PetService createdService = this.serviceService.createService(service);
        return ResponseEntity.created(null).body(createdService);
    }

    // update service
    @PutMapping("/{id}")
    public ResponseEntity<PetService> updateService(@PathVariable Long serviceId, @RequestBody PetService service) {
        try {
           PetService updatedService = this.serviceService.updateService(serviceId, service);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}