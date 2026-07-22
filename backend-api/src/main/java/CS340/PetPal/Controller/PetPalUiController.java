package CS340.PetPal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import CS340.PetPal.Dto.ProviderSignupDto;
import CS340.PetPal.Service.ProviderService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class PetPalUiController {
    ProviderService providerService; 

    @GetMapping({"", "/"})
    public String homePage(Model model, HttpSession session) {
        session.invalidate();
        ProviderSignupDto dto = new ProviderSignupDto();
        model.addAttribute("dto", dto);
        return "login-page";
    }
    
}
