package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Repository.JobRepository;
import CS340.PetPal.Entity.Job;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return this.jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long jobId) {
        return this.jobRepository.findById(jobId);
    }

    public Job createJob(Job job) {
        return this.jobRepository.save(job);
    }

    public Job updateJob(Long jobId, Job job) {
        Job existingJob = this.jobRepository.findById(jobId).orElse(null);
        if (existingJob == null) {
            throw new RuntimeException("Job not found with id: " + jobId);
        }
        if (!job.getName().isEmpty()) {
            existingJob.setName(job.getName());
        }
        if (job.getTime() != null) {
            existingJob.setTime(job.getTime());
        }
        if (!job.getDuration().isEmpty()) {
            existingJob.setDuration(job.getDuration());
        }
        if (job.getPrice() != 0) {
            existingJob.setPrice(job.getPrice());
        }
        return this.jobRepository.save(existingJob);
    }
}
