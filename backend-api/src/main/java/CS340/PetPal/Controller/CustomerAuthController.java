package CS340.PetPal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import CS340.PetPal.Dto.CustomerSignupDto;
import CS340.PetPal.Dto.LoginDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Service.CustomerAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerAuthController {
    private final CustomerAuthService customerAuthService;

    public CustomerAuthController(CustomerAuthService customerAuthService) {
        this.customerAuthService = customerAuthService;
    }

    @GetMapping({ "/sign-up", "/sign-up/" })
    public String signupForm(Model model) {
        if (!model.containsAttribute("customerSignup")) {
            model.addAttribute("customerSignup", new CustomerSignupDto());
        }
        return "customer/sign-up";
    }

    @PostMapping({ "/sign-up", "/sign-up/" })
    public String signup(@ModelAttribute CustomerSignupDto customerSignup,
            RedirectAttributes redirectAttributes) {
        try {
            customerAuthService.registerCustomer(customerSignup);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Your customer account was created. You can log in now.");
            redirectAttributes.addFlashAttribute("email", customerSignup.getEmail());
            return "redirect:/";
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("signupError", exception.getMessage());
            // Keep the typed profile fields, but never place passwords in flash/session data.
            CustomerSignupDto safeFormData = new CustomerSignupDto(
                    customerSignup.getName(),
                    customerSignup.getEmail(),
                    customerSignup.getPhone(),
                    customerSignup.getImageUrl(),
                    null,
                    null);
            redirectAttributes.addFlashAttribute("customerSignup", safeFormData);
            return "redirect:/customer/sign-up";
        }
    }

    @PostMapping({ "/login", "/login/" })
    public String login(@ModelAttribute LoginDto login,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        return customerAuthService.authenticateCustomer(login.getEmail(), login.getPassword())
                .map(customer -> startCustomerSession(customer, request))
                .orElseGet(() -> invalidLogin(login, redirectAttributes));
    }

    @PostMapping({ "/logout", "/logout/" })
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    private String startCustomerSession(Customer customer, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        request.changeSessionId();
        session.removeAttribute("providerId");
        session.setAttribute("customerId", customer.getId());
        return "redirect:/customer/" + customer.getId() + "/dashboard";
    }

    private String invalidLogin(LoginDto login, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("email", login.getEmail());
        redirectAttributes.addFlashAttribute("loginError", "Invalid email or password.");
        return "redirect:/";
    }
}
