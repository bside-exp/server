package bundang.exp.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/exp_offers")
    public String expOffers() {
        return "/expOfferList.html";
    }

    @GetMapping("/exp_offers/{id}")
    public String expOffer(@PathVariable String id) {
        return "/expOffer.html";
    }

    @GetMapping("/exp_requests")
    public String expRequests() {
        return "/expRequestList.html";
    }

    @GetMapping("/exp_requests/{id}")
    public String expRequest(@PathVariable String id) {
        return "/expRequest.html";
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
