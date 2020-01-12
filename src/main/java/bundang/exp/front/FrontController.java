package bundang.exp.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/join")
    public String join() {
        return "join.html";
    }

    @GetMapping("/term")
    public String term() {
        return "term.html";
    }
}
