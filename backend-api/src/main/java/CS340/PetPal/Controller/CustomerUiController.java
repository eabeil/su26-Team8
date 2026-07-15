package CS340.PetPal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import CS340.PetPal.Dto.ReviewCreateDto;
import CS340.PetPal.Service.CustomerService;
import CS340.PetPal.Service.PetService;
import CS340.PetPal.Service.ProviderService;
import CS340.PetPal.Service.ReviewService;


@Controller
@RequestMapping("/customer")
public class CustomerUiController {
    private final CustomerService customerService;
    private final PetService petService;
    private final ReviewService reviewService;
    private final ProviderService providerService;

    public CustomerUiController(CustomerService customerService, PetService petService, ReviewService reviewService, ProviderService providerService) {
        this.customerService = customerService;
        this.petService = petService;
        this.reviewService = reviewService;
        this.providerService = providerService;
    }

    @GetMapping("/{customerId}/providers")
    public String providerDirectory(@PathVariable long customerId, Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("providers", providerService.getAllProviders());
        return "provider-directory";
    }

    @GetMapping("/{customerId}/providers/{providerId}")
    public String providerProfile(@PathVariable Long customerId,
            @PathVariable Long providerId, Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("provider", providerService.getProviderById(providerId));
        model.addAttribute("jobs", providerService.getProviderJobs(providerId));
        model.addAttribute("reviews", providerService.getProviderReviews(providerId));

        return "provider-profile";
    }

    @PostMapping("/{customerId}/providers/{providerId}/reviews")
    public String createReview(@PathVariable Long customerId, @PathVariable Long providerId, @RequestParam Boolean recommended, @RequestParam String customerComment,
            RedirectAttributes redirectAttributes) {
        ReviewCreateDto dto = new ReviewCreateDto(
                recommended, customerComment, customerId, providerId);

        reviewService.createReview(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Your review was posted.");

        return "redirect:/customer/" + customerId + "/providers/" + providerId + "#reviews";
    }

    
}
