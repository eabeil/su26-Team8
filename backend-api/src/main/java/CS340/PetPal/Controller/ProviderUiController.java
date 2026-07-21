package CS340.PetPal.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import CS340.PetPal.Dto.CustomerSearchQueryDto;
import CS340.PetPal.Dto.JobCreateDto;
import CS340.PetPal.Dto.JobUiCreateDto;
import CS340.PetPal.Dto.JobUpdateDto;
import CS340.PetPal.Dto.LoginDto;
import CS340.PetPal.Dto.ProviderCreateDto;
import CS340.PetPal.Dto.ProviderSignupDto;
import CS340.PetPal.Dto.ProviderUiUpdateDto;
import CS340.PetPal.Dto.ProviderUpdateDto;
import CS340.PetPal.Dto.ReviewEditResponseDto;
import CS340.PetPal.Dto.ReviewRespondDto;
import CS340.PetPal.Dto.UpdateCreateDto;
import CS340.PetPal.Dto.UpdateUiCreateDto;
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
import CS340.PetPal.Service.ValidationService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/provider")
public class ProviderUiController {

    ProviderService providerService;
    PetService petService;
    JobService jobService;
    UpdateService updateService;
    ReviewService reviewService;
    CustomerService customerService;
    ValidationService validationService;
    PasswordEncoder passwordEncoder;

    public static String getUrl(Long providerId) {
        return "/provider/" + providerId;
    }

    public static String getUrl(Long providerId, String page) {
        return "/provider/" + providerId + "/" + page;
    }

    public static String getUrl() {
        return "/provider/";
    }

    public static String getUrl(String page) {
        return "/provider/" + page;
    }

    public static String getRedirect(Long providerId) {
        return "redirect:" + ProviderUiController.getUrl(providerId);
    }

    public static String getRedirect(Long providerId, String page) {
        return "redirect:" + ProviderUiController.getUrl(providerId, page);
    }

    public static String getRedirect() {
        return "redirect:" + ProviderUiController.getUrl();
    }

    public static String getRedirect(String page) {
        return "redirect:" + ProviderUiController.getUrl(page);
    }

    public static String getTemplate(String name) {
        return "/provider/" + name;
    }

    @GetMapping({ "/{providerId}/", "{providerId}" })
    public String providerIndex(@PathVariable Long providerId) {
        return ProviderUiController.getRedirect(providerId, "dashboard");
    }

    @GetMapping({ "sign-up", "sign-up/" })
    public String signup(Model model) {
        ProviderSignupDto dto = new ProviderSignupDto();
        model.addAttribute("dto", dto);
        return ProviderUiController.getTemplate("sign-up");
    }

    @GetMapping({ "{providerId}/customer-profile/{customerId}", "{providerId}/customer-profile/{customerId}/" })
    public String customerProfile(@PathVariable Long providerId, @PathVariable Long customerId, Model model, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Provider provider = this.providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        Customer customer = this.customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        List<Pet> pets = this.customerService.getCustomerPets(customerId);
        model.addAttribute("pets", pets);
        return ProviderUiController.getTemplate("customer-profile");
    }

    @GetMapping({ "{providerId}/dashboard", "{providerId}/dashboard/" })
    public String dashboard(@PathVariable Long providerId, Model model, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Provider provider = this.providerService.getProviderById(providerId);
        List<Update> updates = this.providerService.getProviderUpdates(providerId);
        List<Job> jobs = this.providerService.getProviderJobs(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("updates", updates);
        model.addAttribute("jobs", jobs);
        UpdateUiCreateDto updateCreateDto = new UpdateUiCreateDto();
        model.addAttribute("updateCreateDto", updateCreateDto);
        return ProviderUiController.getTemplate("dashboard");
    }

    @GetMapping({ "{providerId}/profile-edit", "{providerId}/profile-edit/" })
    public String editProfile(@PathVariable Long providerId, Model model, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Provider provider = this.providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        ProviderUiUpdateDto providerUpdateDto = new ProviderUiUpdateDto();
        model.addAttribute("jobCreateDto", providerUpdateDto);
        return ProviderUiController.getTemplate("profile-edit");
    }

    @GetMapping({ "{providerId}/jobs-edit", "{providerId}/jobs-edit/" })
    public String editJobs(@PathVariable Long providerId, Model model, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Provider provider = this.providerService.getProviderById(providerId);
        List<Job> jobs = this.providerService.getProviderJobs(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("jobs", jobs);
        JobUiCreateDto jobCreateDto = new JobUiCreateDto();
        model.addAttribute("jobCreateDto", jobCreateDto);
        return ProviderUiController.getTemplate("jobs-edit");
    }

    @GetMapping({ "{providerId}/pet-profile/{petId}", "{providerId}/pet-profile/{petId}" })
    public String petProfile(@PathVariable Long providerId, @PathVariable Long petId, Model model, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Provider provider = this.providerService.getProviderById(providerId);
        Pet pet = this.petService.getPetById(petId);
        model.addAttribute("provider", provider);
        model.addAttribute("pet", pet);
        return ProviderUiController.getTemplate("pet-profile");
    }

    @GetMapping({ "{providerId}/reviews", "{providerId}/reviews/" })
    public String reviews(@PathVariable Long providerId, Model model, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Provider provider = this.providerService.getProviderById(providerId);
        List<Review> reviews = this.providerService.getProviderReviews(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("reviews", reviews);
        ReviewRespondDto reviewRespondDto = new ReviewRespondDto();
        model.addAttribute("reviewRespondDto", reviewRespondDto);
        return ProviderUiController.getTemplate("reviews");
    }

    @GetMapping({ "{providerId}/customer-list", "{providerId}/customer-list/" })
    public String customersList(@PathVariable Long providerId, @RequestParam(required = false) String nameQuery,
            Model model, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Provider provider = this.providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        List<Customer> customers;
        if (nameQuery == null) {
            nameQuery = "";
        }
        model.addAttribute("nameQuery", nameQuery);
        nameQuery = nameQuery.trim();
        if (nameQuery.isEmpty()) {
            customers = this.customerService.getAllCustomers();
        } else {
            customers = this.customerService.getAllCustomersOfName(nameQuery);
        }
        model.addAttribute("customers", customers);
        CustomerSearchQueryDto dto = new CustomerSearchQueryDto();
        model.addAttribute("dto", dto);
        return ProviderUiController.getTemplate("customer-list");
    }

    @GetMapping({ "login", "login/" })
    public String login(@ModelAttribute LoginDto dto, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!this.providerService.getProviderEmailTaken(dto.getEmail())) {
            redirectAttributes.addFlashAttribute("email", dto.getEmail());
            redirectAttributes.addFlashAttribute("emailError", "no provider with email");
            return "redirect:/";
        }
        Provider provider = this.providerService.getProviderByEmail(dto.getEmail());
        if (!this.passwordEncoder.matches(dto.getPassword(), provider.getPassword())) {
            redirectAttributes.addFlashAttribute("email", dto.getEmail());
            redirectAttributes.addFlashAttribute("passwordError", "invalid password");
            return "redirect:/";
        }
        session.setAttribute("providerId", provider.getId());
        return ProviderUiController.getRedirect(provider.getId(), "dashboard");
    }

    @PostMapping({ "create", "create/" })
    public String create(@ModelAttribute ProviderSignupDto dto, RedirectAttributes redirectAttributes) {
        String email = dto.getEmail();
        if (this.providerService.getProviderEmailTaken(email)) {
            redirectAttributes.addFlashAttribute("emailError", "email is already taken");
            return ProviderUiController.getRedirect("sign-up");
        }
        ProviderCreateDto service_dto = new ProviderCreateDto(dto.getName(), dto.getDescription(), dto.getImageUrl(),
                dto.getAddress(), dto.getPhone(), dto.getEmail(), dto.getPassword());
        Provider provider = this.providerService.createProvider(service_dto);
        return ProviderUiController.getRedirect(provider.getId(), "dashboard");
    }

    @PostMapping({ "{providerId}/edit", "{providerId}/edit/" })
    public String editProvider(@PathVariable Long providerId, @ModelAttribute ProviderUiUpdateDto dto,
            RedirectAttributes redirectAttributes, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        String email = dto.getEmail().trim();
        Provider provider = this.providerService.getProviderById(providerId);
        if (!provider.getEmail().equals(email)) {
            if (this.providerService.getProviderEmailTaken(email)) {
                redirectAttributes.addFlashAttribute("emailError", "Email is already taken");
                return ProviderUiController.getRedirect(providerId, "profile-edit");
            }
        }
        ProviderUpdateDto update_dto = new ProviderUpdateDto(dto.getName(), dto.getDescription(), dto.getImageUrl(),
                dto.getAddress(), dto.getPhone(), dto.getEmail(), dto.getPassword());
        this.providerService.updateProvider(providerId, update_dto);
        return ProviderUiController.getRedirect(providerId, "dashboard");
    }

    @GetMapping({ "{providerId}/delete", "{providerId}/delete/" })
    public String deleteProvider(@PathVariable Long providerId, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        this.providerService.deleteProvider(providerId);
        return "redirect:/";
    }

    @PostMapping({ "{providerId}/update/create", "{providerId}/update/create/" })
    public String createUpdate(@PathVariable Long providerId, @ModelAttribute UpdateUiCreateDto dto, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        UpdateCreateDto service_dto = new UpdateCreateDto(dto.getTitle(), dto.getDescription(), dto.getImageUrl(),
                providerId);
        this.updateService.createUpdate(service_dto);
        return ProviderUiController.getRedirect(providerId, "dashboard") + "#scrolly";
    }

    @GetMapping({ "{providerId}/update/{updateId}/delete", "{providerId}/update/{updateId}/delete/" })
    public String deleteUpdate(@PathVariable Long providerId, @PathVariable Long updateId, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Update update = this.updateService.getUpdateById(updateId);
        if (!providerId.equals(update.getProvider().getId())) {
           throw new ResponseStatusException(HttpStatus.FORBIDDEN, "update not owned by provider");
        }
        this.updateService.deleteUpdate(updateId);
        return ProviderUiController.getRedirect(providerId, "dashboard") + "#scrolly";
    }

    @PostMapping({ "{providerId}/job/create", "{providerId}/job/create/" })
    public String createJob(@PathVariable Long providerId, @ModelAttribute JobUiCreateDto dto, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        JobCreateDto service_dto = new JobCreateDto(dto.getName(), dto.getTime(), dto.getDuration(), dto.getPrice(),
                providerId);
        this.jobService.createJob(service_dto);
        return ProviderUiController.getRedirect(providerId, "jobs-edit") + "#scrolly";
    }

    @GetMapping({ "{providerId}/job/{jobId}/edit", "{providerId}/job/{jobId}/edit/" })
    public String editJob(@PathVariable Long providerId, @PathVariable Long jobId, @ModelAttribute JobUpdateDto dto,
            Model model, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Job job = this.jobService.getJobById(jobId);
        if (!jobId.equals(job.getProvider().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "job not owned by provider");
        }
        this.jobService.updateJob(jobId, dto);
        return ProviderUiController.getRedirect(providerId, "edit-jobs");
    }

    @GetMapping({ "{providerId}/job/{jobId}/delete", "{providerId}/job/{jobId}/delete/" })
    public String deleteJob(@PathVariable Long providerId, @PathVariable Long jobId, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Job job = this.jobService.getJobById(jobId);
        if (!jobId.equals(job.getProvider().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "job not owned by provider");
        }
        this.jobService.deleteJob(jobId);
        return ProviderUiController.getRedirect(providerId, "edit-jobs");
    }

    @PostMapping({ "{providerId}/review/{reviewId}/create-response",
            "{providerId}/review/{reviewId}/create-response/" })
    public String createReviewResponse(@PathVariable Long providerId, @PathVariable Long reviewId,
            @ModelAttribute ReviewRespondDto dto, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Review review = this.reviewService.getReviewById(reviewId);
        if (!reviewId.equals(review.getProvider().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "review not owned by provider");
        }
        this.reviewService.respondReview(reviewId, dto);
        return ProviderUiController.getRedirect(providerId, "reviews");
    }

    @GetMapping({ "{providerId}/review/{reviewId}/edit-response", "{providerId}/review/{reviewId}/edit-response/" })
    public String editReviewResponse(@PathVariable Long providerId, @PathVariable Long reviewId,
            @ModelAttribute ReviewEditResponseDto dto, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        Review review = this.reviewService.getReviewById(reviewId);
        if (!reviewId.equals(review.getProvider().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "review not owned by provider");
        }
        this.reviewService.editReviewResponse(reviewId, dto);
        return ProviderUiController.getRedirect(providerId, "reviews");
    }

    @GetMapping({ "{providerId}/customer-list/search", "{providerId}/customer-list/search/ " })
    public String searchCustomers(@PathVariable Long providerId, @ModelAttribute CustomerSearchQueryDto dto, HttpSession session) {
        if (!providerId.equals(session.getAttribute("providerId"))) {
            return "redirect:/";
        }
        String nameQuery = dto.getName().trim();
        return "redirect:" + UriComponentsBuilder.fromPath(ProviderUiController.getUrl(providerId, "customer-list"))
                .queryParam("name_query", nameQuery).encode().toString();
    }
}
