package CS340.PetPal.Controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import CS340.PetPal.Dto.CustomerUpdateDto;
import CS340.PetPal.Dto.PetCreateDto;
import CS340.PetPal.Dto.PetUpdateDto;
import CS340.PetPal.Dto.ReviewCreateDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Service.CustomerDirectoryService;
import CS340.PetPal.Service.CustomerService;
import CS340.PetPal.Service.PetService;
import CS340.PetPal.Service.ProviderService;
import CS340.PetPal.Service.ReviewService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerUiController {
    private final CustomerService customerService;
    private final PetService petService;
    private final ReviewService reviewService;
    private final ProviderService providerService;
    private final CustomerDirectoryService customerDirectoryService;

    public CustomerUiController(CustomerService customerService, PetService petService, ReviewService reviewService,
            ProviderService providerService, CustomerDirectoryService customerDirectoryService) {
        this.customerService = customerService;
        this.petService = petService;
        this.reviewService = reviewService;
        this.providerService = providerService;
        this.customerDirectoryService = customerDirectoryService;
    }

    @GetMapping("/{customerId}/providers")
    public String providerDirectory(@PathVariable Long customerId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String service,
            @RequestParam(required = false) Double maxRate,
            @RequestParam(defaultValue = "recommended") String sort,
            Model model, HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }
        model.addAttribute("customerId", customerId);
        model.addAttribute("providers",
                customerDirectoryService.findProviders(keyword, location, service, maxRate, sort));
        model.addAttribute("keyword", valueOrEmpty(keyword));
        model.addAttribute("location", valueOrEmpty(location));
        model.addAttribute("service", valueOrEmpty(service));
        model.addAttribute("maxRate", maxRate);
        model.addAttribute("sort", sort);
        return "provider-directory";
    }

    @GetMapping("/{customerId}/pets/new")
    public String createPetForm(@PathVariable Long customerId, Model model, HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }
        customerService.getCustomerById(customerId);
        model.addAttribute("customerId", customerId);
        model.addAttribute("pageTitle", "Add a New Pet");
        model.addAttribute("formAction", "/customer/" + customerId + "/pets");
        model.addAttribute("pet", new Pet());
        return "pet-form";
    }

    @PostMapping("/{customerId}/pets")
    public String createPet(@PathVariable Long customerId,
            @RequestParam String name,
            @RequestParam String speciesOrBreed,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String imageUrl,
            @RequestParam String specialCareInstructions,
            @RequestParam String traits,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }

        PetCreateDto dto = new PetCreateDto(name, speciesOrBreed, age, imageUrl,
                specialCareInstructions, traits, customerId);

        try {
            petService.createPet(dto);
            redirectAttributes.addFlashAttribute("successMessage", name.trim() + "'s profile was created.");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/customer/" + customerId + "/pets/new";
        }

        return dashboardRedirect(customerId);
    }

    @GetMapping("/{customerId}/pets/{petId}/edit")
    public String updatePetForm(@PathVariable Long customerId,
            @PathVariable Long petId, Model model, HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }
        Pet pet = petService.getCustomerPet(customerId, petId);
        model.addAttribute("customerId", customerId);
        model.addAttribute("pageTitle", "Update " + pet.getName());
        model.addAttribute("formAction", "/customer/" + customerId + "/pets/" + petId);
        model.addAttribute("pet", pet);

        return "pet-form";
    }

    @PostMapping("/{customerId}/pets/{petId}")
    public String updatePet(@PathVariable Long customerId,
            @PathVariable Long petId,
            @RequestParam String name,
            @RequestParam String speciesOrBreed,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String imageUrl,
            @RequestParam String specialCareInstructions,
            @RequestParam String traits,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }

        PetUpdateDto dto = new PetUpdateDto(name, speciesOrBreed, age, imageUrl,
                specialCareInstructions, traits);

        try {
            petService.updateCustomerPet(customerId, petId, dto);
            redirectAttributes.addFlashAttribute("successMessage", name.trim() + "'s profile was updated.");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/customer/" + customerId + "/pets/" + petId + "/edit";
        }

        return dashboardRedirect(customerId);
    }

    @PostMapping("/{customerId}/pets/{petId}/delete")
    public String deletePet(@PathVariable Long customerId, @PathVariable Long petId,
            HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }

        Pet pet = petService.getCustomerPet(customerId, petId);
        petService.deleteCustomerPet(customerId, petId);
        redirectAttributes.addFlashAttribute("successMessage", pet.getName() + "'s profile was deleted.");
        return dashboardRedirect(customerId);
    }

    @GetMapping("/{customerId}/dashboard")
    public String dashboard(@PathVariable Long customerId, Model model, HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        model.addAttribute("customerId", customerId);
        model.addAttribute("pets", customerService.getCustomerPets(customerId));
        model.addAttribute("providers", providerService.getAllProviders());
        
        return "customer-dashboard";
    }

    @GetMapping("/{customerId}/providers/{providerId}")
    public String providerProfile(@PathVariable Long customerId,
            @PathVariable Long providerId, Model model, HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }
        model.addAttribute("customerId", customerId);
        model.addAttribute("provider", providerService.getProviderById(providerId));
        model.addAttribute("jobs", providerService.getProviderJobs(providerId));
        model.addAttribute("reviews", providerService.getProviderReviews(providerId));

        return "provider-profile";
    }

    @PostMapping("/{customerId}/providers/{providerId}/reviews")
    public String createReview(@PathVariable Long customerId, @PathVariable Long providerId,
            @RequestParam Boolean recommended, @RequestParam String customerComment,
            RedirectAttributes redirectAttributes, HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }
        ReviewCreateDto dto = new ReviewCreateDto(
                recommended, customerComment, customerId, providerId);

        try {
            reviewService.createReview(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Your review was posted.");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        }

        return "redirect:/customer/" + customerId + "/providers/" + providerId + "#reviews";
    }

    @PostMapping("/{customerId}/providers/{providerId}/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable Long customerId, @PathVariable Long providerId,
            @PathVariable Long reviewId, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }

        reviewService.deleteCustomerReview(customerId, providerId, reviewId);
        redirectAttributes.addFlashAttribute("successMessage", "Your review was deleted.");
        return "redirect:/customer/" + customerId + "/providers/" + providerId + "#reviews";
    }

    private String dashboardRedirect(Long customerId) {
        return "redirect:/customer/" + customerId + "/dashboard#pets";
    }

    @GetMapping("/{customerId}/profile")
    public String updateProfileForm(
            @PathVariable Long customerId,
            Model model,
            HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }

        Customer customer = customerService.getCustomerById(customerId);

        model.addAttribute("customerId", customerId);
        model.addAttribute("pageTitle", "Edit Profile");
        model.addAttribute("formAction", "/customer/" + customerId + "/profile");
        model.addAttribute("profile", customer);

        return "customer-profile";
    }

    @PostMapping("/{customerId}/profile")
    public String updateProfile(
            @PathVariable Long customerId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String location,
            @RequestParam(required = false) String imageUrl,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }

        CustomerUpdateDto dto = new CustomerUpdateDto(name, email, phone, location, imageUrl);

        try {
            customerService.updateCustomer(customerId, dto);
            redirectAttributes.addFlashAttribute("successMessage", name.trim() + "'s profile was updated.");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        }

        return "redirect:/customer/" + customerId + "/profile";
    }

    @PostMapping("/{customerId}/delete")
    public String deleteCustomer(@PathVariable Long customerId, HttpSession session) {
        if (!isCurrentCustomer(customerId, session)) {
            return loginRedirect();
        }

        customerService.deleteCustomer(customerId);
        session.invalidate();
        return "redirect:/?accountDeleted=true";
    }

    private boolean isCurrentCustomer(Long customerId, HttpSession session) {
        return Objects.equals(customerId, session.getAttribute("customerId"))
                && session.getAttribute("providerId") == null;
    }

    private String loginRedirect() {
        return "redirect:/";
    }

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}
