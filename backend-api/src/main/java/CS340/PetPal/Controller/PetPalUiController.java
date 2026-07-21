package CS340.PetPal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import CS340.PetPal.Service.ProviderService;

@Controller
@RequestMapping
public class PetPalUiController {
    ProviderService providerService; 

    @GetMapping({"", "/"})
    public String homePage() {
        return "login-page";
    }
}
