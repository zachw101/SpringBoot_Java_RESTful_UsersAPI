package com.yams.userapiv2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepository repository;

	@GetMapping("/customer")
	List<Customer> allItems() {
		return repository.findAll();
	}

	@PostMapping("/customer/add")
	Customer addCustomer(@RequestBody Customer entry) {
		return repository.save(entry);
	}

	@PutMapping("/customer/update")
	Customer updateCustomer(@RequestBody Customer entry, @RequestParam String email) {
		Customer cust = repository.findByEmail(email);
			cust.setUsername(entry.getUsername());
			cust.setEmail(entry.getEmail());
			return repository.save(cust);
	}
	
	@GetMapping("/customer/login")
	Customer customerLogin(@RequestParam String email) {
		return repository.findByEmail(email);
	}
	
	@DeleteMapping("/customer/delete")
	void deleteItem(@RequestParam String email) {
			Customer customer = repository.findByEmail(email);
			repository.delete(customer);
	}

}
