package springApp;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("springApp.core.domain.entities")
public class Program {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        Program.applicationContext = SpringApplication.run(Program.class, args);
    }
}
