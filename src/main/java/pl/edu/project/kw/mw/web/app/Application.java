package pl.edu.project.kw.mw.web.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.edu.project.kw.mw.persistence.DatabaseConnector;


import javax.annotation.PreDestroy;

@SpringBootApplication
@ComponentScan(basePackages = "pl.edu.project.kw.mw.web.controllers")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PreDestroy
    public void stop() {
        DatabaseConnector.getInstance().teardown();
    }
}