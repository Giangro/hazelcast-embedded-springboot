package guides.hazelcast.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HazelcastApplication {
    public static void main(String[] args) {        
        System.out.println("*** Versione 0.1 ***");
        SpringApplication.run(HazelcastApplication.class, args);
    }
}
