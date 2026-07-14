package CS340.PetPal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CS340.PetPal.Dto.ProviderUpdateDto;
import CS340.PetPal.Dto.UpdateUpdateDto;

@Controller
@RequestMapping("/provider/{providerId}")
public class ProviderUiController {

   @GetMapping({"/", ""})
   public String providerIndex(@PathVariable Long providerId) {
    return "redirect:/provider/" + providerId + "/dashboard";
   }

    @GetMapping({"/customer-profile", "/customer-profile/"})
    public String customerProfile(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId);
        return "provider/customer-profile";
    }

    @GetMapping({"/dashboard", "/dashboard/"})
    public String dashboard(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId);
        return "provider/dashboard";
    }

    @GetMapping({"/edit-profile", "/edit-profile/"})
    public String editProfile(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId);
        return "provider/edit-profile";
    }

    @GetMapping({"/edit-services", "/edit-services"})
    public String editServices(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId);
        return "provider/edit-services";
    }

    @GetMapping({"/pet-profile", "/pet-profile/"})
    public String petProfile(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId);
        return "provider/pet-profile";
    }

    @GetMapping({"/respond-reviews", "/respond-reviews/"})
    public String respondReviews(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId);
        return "provider/respond-reviews";
    }

    @GetMapping({"/search-customers", "/search-customers/"})
    public String searchCustomers(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId);
        return "provider/search-customers";
    }
}
