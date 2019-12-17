package bundang.exp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class ExpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpApplication.class, args);
    }

}
