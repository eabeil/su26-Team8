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

import CS340.PetPal.Service.JobService;
import CS340.PetPal.Dto.CreateJobDto;
import CS340.PetPal.Dto.UpdateJobDto;
import CS340.PetPal.Entity.Job;

@RestController
@RequestMapping("/api/jobs")
public class JobApiController {

    private final JobService jobService;

    public JobApiController(JobService jobService) {
        this.jobService = jobService;
    }

    // get jobs
    @GetMapping("/")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> services = this.jobService.getAllJobs();
        return ResponseEntity.ok(services);
    }

    // get job
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
    @PostMapping("/")
    public ResponseEntity<Job> createJob(@RequestBody CreateJobDto dto) {
        Job createdJob = this.jobService.createJob(dto);
        URI location = URI.create("/api/services/" + createdJob.getId());
        return ResponseEntity.created(location).body(createdJob);
    }

    // update job
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") Long jobId, @RequestBody UpdateJobDto dto) {
        try {
            Job updatedJob = this.jobService.updateJob(jobId, dto);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // delete job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable("id") Long jobId) {
        boolean deletedOk = this.jobService.deleteJob(jobId);
        if (!deletedOk) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}