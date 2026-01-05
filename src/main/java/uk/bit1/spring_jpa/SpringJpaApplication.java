package uk.bit1.spring_jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringJpaApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few Customers
			repository.save(new Customer("Smith", "John"));
			repository.save(new Customer("Waters", "Anthony"));
			repository.save(new Customer("Waters", "John"));
			repository.save(new Customer("Waters", "Annie"));
			repository.save(new Customer("Waters", "Tony"));

			// fetch all customers
			logger.info("Customers found with findAll()");
			logger.info("------------------------------");
			List<Customer> customers = (List<Customer>) repository.findAll();
			logger.info(customers.toString());
			logger.info("");

			// fetch customer by id
			logger.info("Customers found with findById(1L)");
			logger.info("------------------------------");
			Customer customer = repository.findById(1L);
			logger.info(customer.toString());
			logger.info("");

			// fetch customer by last name
			logger.info("Customers found with findByLastName(String)");
			logger.info("------------------------------");
			customers = repository.findByLastName("Waters");
			logger.info(customers.toString());
			logger.info("");
		};
	}

}
