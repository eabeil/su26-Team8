package CS340.PetPal.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            List<Job> services = this.jobService.getAllJobs();
            return ResponseEntity.ok(services);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get job
    @GetMapping("/{id}/")
    public ResponseEntity<Job> getJobById(@PathVariable("id") Long jobId) {
        try {
            Job job = this.jobService.getJobById(jobId);
            return ResponseEntity.ok(job);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // create job
    @PostMapping("/")
    public ResponseEntity<Job> createJob(@RequestBody CreateJobDto dto) {
        try {
            Job job = this.jobService.createJob(dto);
            URI location = URI.create("/api/services/" + job.getId());
            return ResponseEntity.created(location).body(job);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // update job
    @PutMapping("/{id}/")
    public ResponseEntity<Job> updateJob(@PathVariable("id") Long jobId, @RequestBody UpdateJobDto dto) {
        try {
            Job updatedJob = this.jobService.updateJob(jobId, dto);
            return ResponseEntity.ok(updatedJob);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // delete job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable("id") Long jobId) {
        try {
            this.jobService.deleteJob(jobId);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
}