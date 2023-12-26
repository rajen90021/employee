package Employe_wise.com;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeWiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeWiseApplication.class, args);
	}

	
	@Bean
	 ModelMapper mapper() {
		 
		 return new ModelMapper();
	 }
	  
}
