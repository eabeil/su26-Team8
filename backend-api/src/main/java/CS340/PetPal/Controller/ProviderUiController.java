package CS340.PetPal.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CS340.PetPal.Dto.JobCreateDto;
import CS340.PetPal.Dto.JobUiCreateDto;
import CS340.PetPal.Dto.JobUpdateDto;
import CS340.PetPal.Dto.ProviderUpdateDto;
import CS340.PetPal.Dto.ReviewEditResponseDto;
import CS340.PetPal.Dto.ReviewRespondDto;
import CS340.PetPal.Dto.UpdateCreateDto;
import CS340.PetPal.Dto.UpdateUiCreateDto;
import CS340.PetPal.Dto.UpdateUpdateDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Entity.Job;
import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Entity.Provider;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Entity.Update;
import CS340.PetPal.Service.CustomerService;
import CS340.PetPal.Service.JobService;
import CS340.PetPal.Service.PetService;
import CS340.PetPal.Service.ProviderService;
import CS340.PetPal.Service.ReviewService;
import CS340.PetPal.Service.UpdateService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/provider/{providerId}")
public class ProviderUiController {

    ProviderService providerService;
    PetService petService;
    JobService jobService;
    UpdateService updateService;
    ReviewService reviewService;
    CustomerService customerService;

   @GetMapping({"/", ""})
   public String providerIndex(@PathVariable Long providerId) {
    return "redirect:/provider/" + providerId + "/dashboard";
   }

    @GetMapping({"/customer-profile", "/customer-profile/"})
    public String customerProfile(@PathVariable Long providerId, Model model) {
        Provider provider = this.providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "/provider/customer-profile";
    }

    @GetMapping({"/dashboard", "/dashboard/"})
    public String dashboard(@PathVariable Long providerId, Model model) {
        Provider provider = this.providerService.getProviderById(providerId);
        List<Update> updates = this.providerService.getProviderUpdates(providerId);
        List<Job> jobs = this.providerService.getProviderJobs(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("updates", updates);
        model.addAttribute("jobs", jobs);
        return "/provider/dashboard";
    }

    @GetMapping({"/profile-edit", "/profile-edit/"})
    public String editProfile(@PathVariable Long providerId, Model model) {
        Provider provider = this.providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "/provider/profile-edit";
    }

    @GetMapping({"/jobs-edit", "/jobs-edit"})
    public String editJobs(@PathVariable Long providerId, Model model) {
        Provider provider = this.providerService.getProviderById(providerId);
        List<Job> jobs = this.providerService.getProviderJobs(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("jobs", jobs);
        return "/provider/jobs-edit";
    }

    @GetMapping({"/pet-profile/{petId}", "/pet-profile/{petId}"})
    public String petProfile(@PathVariable Long providerId, @PathVariable Long petId, Model model) {
        Provider provider = this.providerService.getProviderById(providerId);
        Pet pet = this.petService.getPetById(petId);
        model.addAttribute("provider", provider);
        model.addAttribute("pet", pet);
        return "/provider/pet-profile";
    }

    @GetMapping({"/reviews", "/reviews/"})
    public String reviews(@PathVariable Long providerId, Model model) {
        Provider provider = this.providerService.getProviderById(providerId);
        List<Review> reviews = this.providerService.getProviderReviews(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("reviews", reviews);
        return "/provider/reviews";
    }

    @GetMapping({"/customers-list", "/customers-list/"})
    public String customersList(@PathVariable Long providerId, Model model) {
        Provider provider = this.providerService.getProviderById(providerId);
        List<Customer> customers = this.customerService.getAllCustomers();
        model.addAttribute("provider", provider);
        model.addAttribute("customers", customers);
        return "/provider/customers-list";
    }

    @GetMapping({"/edit", "/edit/"})
    public String editProvider(@PathVariable Long providerId, @ModelAttribute ProviderUpdateDto dto) {
        this.providerService.updateProvider(providerId, dto);
        return "redirect:/provider/" + providerId + "/dashboard";        
    }

    @GetMapping({"/delete", "/delete/"})
    public String deleteProvider(@PathVariable Long providerId, Model model) {
        this.providerService.deleteProvider(providerId);
        return "redirect:/";
    }

    @GetMapping({"/update/create", "/update/create/"})
    public String createUpdate(@PathVariable Long providerId, @ModelAttribute UpdateUiCreateDto dto) {
        UpdateCreateDto service_dto = new UpdateCreateDto(dto.getTitle(), dto.getDescription(), providerId);
        this.updateService.createUpdate(service_dto);
        return "redirect:/provider/" + providerId + "/dashboard";
    }

    @GetMapping({"/update/{updateId}/edit", "/update/{updateId}/edit/"})
    public String editUpdate(@PathVariable Long providerId, @PathVariable Long updateId, @ModelAttribute UpdateUpdateDto dto) {
        this.updateService.updateUpdate(updateId, dto);
        return "redirect:/provider/" + providerId + "/dashboard";
    }

    @GetMapping({"/update/{updateId}/delete", "/update/{updateId}/delete/"})
    public String deleteUpdate(@PathVariable Long providerId, @PathVariable Long updateId) {
        this.updateService.deleteUpdate(updateId);
        return "redirect:/provider/" + providerId + "/dashboard";
    }

    @GetMapping({"/job/create", "/job/create/"})
    public String createJob(@PathVariable Long providerId, @ModelAttribute JobUiCreateDto dto, Model model) {
        JobCreateDto service_dto = new JobCreateDto(dto.getName(), dto.getTime(), dto.getDuration(), dto.getPrice(), providerId);
        this.jobService.createJob(service_dto);
        return "redirect:/provider/" + providerId + "/dashboard";
    }

    @GetMapping({"/job/{jobId}/edit", "/job/{jobId}/edit/"})
    public String editJob(@PathVariable Long providerId, @PathVariable Long jobId, @ModelAttribute JobUpdateDto dto, Model model) {
        this.jobService.updateJob(jobId, dto);
        return "redirect:/provider/" + providerId + "/dashboard";
    }

    @GetMapping({"/job/{jobId}/delete", "/job/{jobId}/delete/"})
    public String deleteJob(@PathVariable Long providerId, @PathVariable Long jobId, Model model) {
        this.jobService.deleteJob(jobId);
        return "redirect:/provider/" + providerId + "/jobs-edit";
    }

    @GetMapping({"/review/{reviewId}/create-response", "/review/{reviewId}/create-response/"})
    public String createReviewResponse(@PathVariable Long providerId, @PathVariable Long reviewId, @ModelAttribute ReviewRespondDto dto) {
        this.reviewService.respondReview(reviewId, dto);
        return "redirect:/provider/" + providerId + "/reviews";
    }

    @GetMapping({"/review/{reviewId}/edit-response", "/review/{reviewId}/edit-response/"})
    public String editReviewResponse(@PathVariable Long providerId, @PathVariable Long reviewId, @ModelAttribute ReviewEditResponseDto dto) {
        this.reviewService.editReviewResponse(reviewId, dto);
        return "redirect:/provider/" + providerId + "/reviews";
    }
}
