package CS340.PetPal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.CustomerCreateDto;
import CS340.PetPal.Dto.CustomerLoginDto;
import CS340.PetPal.Dto.CustomerResponseDto;
import CS340.PetPal.Service.CustomerSessionService;
import CS340.PetPal.Service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomerUiController {
    private final CustomerService customerService;
    private final CustomerSessionService customerSessionService;

    public CustomerUiController(CustomerService customerService, CustomerSessionService customerSessionService) {
        this.customerService = customerService;
        this.customerSessionService = customerSessionService;
    }

    @GetMapping("/")
    public String loginPage(HttpServletRequest request, Model model) {
        if (customerSessionService.getCustomerId(request) != null) {
            return "redirect:/customer/dashboard";
        }
        model.addAttribute("form", new CustomerLoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("form") CustomerLoginDto form,
            HttpServletRequest request, Model model) {
        try {
            CustomerResponseDto customer = customerService.authenticate(form);
            customerSessionService.login(request, customer.id());
            return "redirect:/customer/dashboard";
        } catch (ResponseStatusException exception) {
            model.addAttribute("error", exception.getReason());
            return "login";
        }
    }

    @GetMapping("/signup")
    public String signupPage(HttpServletRequest request, Model model) {
        if (customerSessionService.getCustomerId(request) != null) {
            return "redirect:/customer/dashboard";
        }
        model.addAttribute("form", new CustomerCreateDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("form") CustomerCreateDto form,
            HttpServletRequest request, Model model) {
        try {
            CustomerResponseDto customer = customerService.createCustomer(form);
            customerSessionService.login(request, customer.id());
            return "redirect:/customer/dashboard";
        } catch (ResponseStatusException exception) {
            model.addAttribute("error", exception.getReason());
            return "signup";
        }
    }

    @GetMapping("/customer/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        Long customerId = customerSessionService.getCustomerId(request);
        if (customerId == null) {
            return "redirect:/";
        }

        try {
            model.addAttribute("customer", customerService.getCustomerById(customerId));
            return "customer-dashboard";
        } catch (ResponseStatusException exception) {
            customerSessionService.logout(request);
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        customerSessionService.logout(request);
        return "redirect:/";
    }
}
