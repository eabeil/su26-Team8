package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Repository.JobRepository;
import CS340.PetPal.Repository.ProviderRepository;
import CS340.PetPal.Dto.JobCreateDto;
import CS340.PetPal.Entity.Job;
import CS340.PetPal.Entity.Provider;
import CS340.PetPal.Dto.JobUpdateDto;

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

    public Job getJobById(Long jobId) {
        Optional<Job> jobO = this.jobRepository.findById(jobId);
        if (jobO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no job with id " + jobId + ".");
        }
        return jobO.get();
    }

    public Job createJob(JobCreateDto dto) {
        Optional<Provider> providerO = this.providerReposotiry.findById(dto.getProviderId());
        if (providerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no provider with id " + dto.getProviderId() + ".");
        }
        Provider provider = providerO.get();
        Job job = new Job(dto.getName(), dto.getTime(), dto.getDuration(), dto.getPrice(), provider);
        return this.jobRepository.save(job);
    }

    public Job updateJob(Long jobId, JobUpdateDto dto) {
        Optional<Job> jobO = this.jobRepository.findById(jobId);
        if (jobO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no job with id " + jobId + ".");
        }
        Job job = jobO.get();
        job.setName(dto.getName());
        job.setTime(dto.getTime());
        job.setDuration(dto.getDuration());
        job.setPrice(dto.getPrice());
        return this.jobRepository.save(job);
    }

    public void deleteJob(Long jobId) {
        Optional<Job> jobO = this.jobRepository.findById(jobId);
        if (jobO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no job with id " + jobId + ".");
        }
        Job job = jobO.get();
        this.jobRepository.delete(job);
    }
}
