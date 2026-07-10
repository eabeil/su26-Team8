package CS340.PetPal.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/api/jobs")
public class JobApiController {

    private final JobService jobService;

    public JobApiController(JobService jobService) {
        this.jobService = jobService;
    }

    // get services
    @GetMapping("/")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> services = this.jobService.getAllJobs();
        return ResponseEntity.ok(services);
    }

    // get service
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") Long serviceId) {
        Optional<Job> jobO = this.jobService.getJobById(serviceId);
        if (jobO.isPresent()) {
            Job job = jobO.get();
            return ResponseEntity.ok(job);
        }
        return ResponseEntity.notFound().build();
    }

    // create job
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = this.jobService.createJob(job);
        URI location = URI.create("/api/services/" + createdJob.getId());
        return ResponseEntity.created(location).body(createdJob);
    }

    // update service
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") Long serviceId, @RequestBody Job service) {
        try {
            Job updatedJob = this.jobService.updateJob(serviceId, service);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}