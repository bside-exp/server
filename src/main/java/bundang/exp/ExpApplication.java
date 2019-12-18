package bundang.exp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@CrossOrigin
public class ExpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
