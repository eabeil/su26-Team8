package CS340.PetPal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class PetPalUiController {

    @GetMapping({"", "/"})
    public String homePage(HttpSession session, @RequestParam(required = false) Boolean accountDeleted, Model model) {
        if (Boolean.TRUE.equals(accountDeleted)) {
            model.addAttribute("successMessage", "Your customer account was deleted.");
        }
        session.invalidate();
        return "login-page";
    }

}
