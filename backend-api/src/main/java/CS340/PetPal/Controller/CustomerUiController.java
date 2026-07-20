package CS340.PetPal.Controller;

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
    public String providerDirectory(@PathVariable Long customerId, Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("providers", providerService.getAllProviders());
        return "provider-directory";
    }


    @GetMapping("/{customerId}/pets/new")
    public String createPetForm(@PathVariable Long customerId, Model model) {
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
            RedirectAttributes redirectAttributes) {
        PetCreateDto dto = new PetCreateDto(name, speciesOrBreed, age, imageUrl,
                specialCareInstructions, traits, customerId);

        petService.createPet(dto);
        redirectAttributes.addFlashAttribute("successMessage", name + "'s profile was created.");

        return dashboardRedirect(customerId);
    }

    @GetMapping("/{customerId}/profile")
    public String updateProfileForm(
        @PathVariable Long customerId,
        Model model) {
    Customer customer = customerService.getCustomerById(customerId);
    model.addAttribute("customerId", customerId);
    model.addAttribute("pageTitle", "Edit Profile");
    model.addAttribute(
            "formAction",
           "/customer/" + customerId + "/profile");
    model.addAttribute("profile", customer);

    return "profile-form";
}




    @GetMapping("/{customerId}/pets/{petId}/edit")
    public String updatePetForm(@PathVariable Long customerId,
            @PathVariable Long petId, Model model) {
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
            RedirectAttributes redirectAttributes) {
        PetUpdateDto dto = new PetUpdateDto(name, speciesOrBreed, age, imageUrl,
                specialCareInstructions, traits);

        petService.updateCustomerPet(customerId, petId, dto);
        redirectAttributes.addFlashAttribute("successMessage", name + "'s profile was updated.");

        return dashboardRedirect(customerId);
    }

@PostMapping("/{customerId}/profile")
public String updateProfile(
        @PathVariable Long customerId,
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String phone,
        @RequestParam(required = false) String imageUrl,
        RedirectAttributes redirectAttributes) {

    CustomerUpdateDto dto =
            new CustomerUpdateDto(name, email, phone, imageUrl);

    customerService.updateCustomer(customerId, dto);

    redirectAttributes.addFlashAttribute(
            "successMessage",
            name + "'s profile was updated.");

    return "redirect:/customer/" + customerId + "/profile";
}


    @GetMapping("/{customerId}/dashboard")
    public String dashboard(@PathVariable Long customerId, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(customerId));
        model.addAttribute("pets", customerService.getCustomerPets(customerId));
        model.addAttribute("providers", providerService.getAllProviders());

        return "customer-dashboard";
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

    private String dashboardRedirect(Long customerId) {
        return "redirect:/customer/" + customerId + "/dashboard#pets";
    }

    
}
