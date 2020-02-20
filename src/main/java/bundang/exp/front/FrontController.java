package bundang.exp.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping("/")
    public String home() {
        return "main.html";
    }

    @GetMapping("/join")
    public String join() {
        return "/join.html";
    }

    @GetMapping("/term")
    public String term() {
        return "/term.html";
    }

    @GetMapping("/login")
    public String page() {
        return "/login.html";
    }

    @GetMapping("/exp_offer")
    public String expOffer() {
        return "/login.html";
    }

    @GetMapping("/exp_offer/regit")
    public String expOfferRegit() {
        return "/expOfferRegit.html";
    }

    @GetMapping("/exp_request/regit")
    public String expRequestRegit() {
        return "/expRequestRegit.html";
    }
}
