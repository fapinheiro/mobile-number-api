/**
 * @author filipe.pinheiro, 29/09/2018
 */
package mt.com.vodafone.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"mt.com.vodafone"})
@EnableJpaRepositories(basePackages={"mt.com.vodafone"}) 
@EntityScan(basePackages={"mt.com.vodafone"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
