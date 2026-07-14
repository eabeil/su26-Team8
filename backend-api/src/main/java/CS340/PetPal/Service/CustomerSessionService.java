package CS340.PetPal.Service;


import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomerSessionService {
    private static final String CUSTOMER_ID_SESSION_KEY = "customerId";

    public void login(HttpServletRequest request, Long customerId) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        request.getSession(true).setAttribute(CUSTOMER_ID_SESSION_KEY, customerId);
    }

    public Long getCustomerId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (Long) session.getAttribute(CUSTOMER_ID_SESSION_KEY);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}