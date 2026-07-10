package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Repository.JobRepository;
import CS340.PetPal.Repository.ProviderRepository;
import CS340.PetPal.DTO.CreateJobDto;
import CS340.PetPal.Entity.Job;
import CS340.PetPal.Entity.Provider;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final ProviderRepository providerReposotiry;

    public JobService(JobRepository jobRepository, ProviderRepository providerRepository) {
        this.jobRepository = jobRepository;
        this.providerReposotiry = providerRepository;
    }

    public List<Job> getAllJobs() {
        return this.jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long jobId) {
        return this.jobRepository.findById(jobId);
    }

    public Job createJob(CreateJobDto dto) {
       Optional<Provider> providerO = this.providerReposotiry.findById(dto.getProviderId());
       if (providerO.isEmpty()) {
        throw new RuntimeException("no provider with id " + dto.getProviderId());
       }
       Provider provider = providerO.get();
       Job job = new Job(dto.getName(), dto.getTime(), dto.getDuration(), dto.getPrice(), provider);
        return this.jobRepository.save(job);
    }

    public Job updateJob(Long jobId, Job job) {
        Optional<Job> existingJobO = this.jobRepository.findById(jobId);
        if (existingJobO.isEmpty()) {
            throw new RuntimeException("Job not found with id: " + jobId);
        }
        Job existingJob = existingJobO.get();
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
