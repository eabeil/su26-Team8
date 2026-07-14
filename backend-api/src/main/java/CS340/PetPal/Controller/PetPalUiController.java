package CS340.PetPal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PetPalUiController {
    @GetMapping({"", "/"})
    public String homePage() {
        return "login-page.ftlh";
    }
    
    @GetMapping({"signup", "signup/"})
    public String signup() {
        return "signup.ftlh";
    }
}
