package clientManager;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
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

	@Bean
	public Docket removeSwaggerErrorEntry() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(Predicates.not(PathSelectors.regex("/error.*")))
			.build();
	}
}
