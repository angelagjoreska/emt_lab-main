package mk.finki.ukim.wp.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibraryapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryapiApplication.class, args);
    }

}
