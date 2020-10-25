package clientManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author Agustin Perez Garcia
 *
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "clientManager")
public class ClientManagerApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ClientManagerApplication.class, args);
	}
}
