package com.LakshaySpring.LearnSpring;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customer")
public class LearnSpringApplication {
	private final  CustomerRepository customerRepository;
	
	public LearnSpringApplication(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	
	

	public static void main(String[] args) {
		System.out.println("app running");
		SpringApplication.run(LearnSpringApplication.class, args);
	}

	@PutMapping("{customerId}")
	public void  updateCustomer(@RequestBody NewCustomerRequest request,@PathVariable("customerId") Integer id){
		Customer customer = customerRepository.getReferenceById(id);
		  	customer.setAge(request.age);
			customer.setEmail(request.email);
			customer.setName(request.name);
			customerRepository.save(customer);
	}

	public record NewCustomerRequest(
		String name,
		String email,
		Integer age
	) {}

	@GetMapping
	public List<Customer> getCustomers() {
		return	customerRepository.findAll();
	}

	
	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
			Customer customer = new Customer();
			customer.setAge(request.age);
			customer.setEmail(request.email);
			customer.setName(request.name);

			customerRepository.save(customer);
		}
	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
	}	

	

	// @GetMapping("/Greet")
	public GreetResponse greet() {
		GreetResponse response = new GreetResponse(
				"Hello",
				List.of("Java", "Golang", "Dart", "Javascript"),
				new Person("Lakshay", 22, 1978));

		return response;
	}

	/**
	 * InnerLearnSpringApplication
	 */
	record GreetResponse(
			String greet,
			List<String> favProgrammingLanguages,
			Person person) {
	}

	/**
	 * InnerLearnSpringApplication
	 */
	record Person(String name, int age, double savings) {
	}

	// class GreetResponse{
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + getEnclosingInstance().hashCode();
	// result = prime * result + ((greet == null) ? 0 : greet.hashCode());
	// return result;
	// }

	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// GreetResponse other = (GreetResponse) obj;
	// if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
	// return false;
	// if (greet == null) {
	// if (other.greet != null)
	// return false;
	// } else if (!greet.equals(other.greet))
	// return false;
	// return true;
	// }

	// @Override
	// public String toString() {
	// return "GreetResponse [greet=" + greet + "]";
	// }

	// private final String greet;

	// public GreetResponse(String greet) {
	// this.greet = greet;
	// }

	// public String getGreet() {
	// return greet;
	// }

	// private LearnSpringApplication getEnclosingInstance() {
	// return LearnSpringApplication.this;
	// }

	// }

}
