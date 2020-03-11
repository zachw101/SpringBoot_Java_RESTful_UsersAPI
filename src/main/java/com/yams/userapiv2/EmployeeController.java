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
public class EmployeeController {

	public String key = "1234567";

	@Autowired
	EmployeeRepository repository;

	// For developing purposes only
	@GetMapping("/employee")
	List<Employee> allItems() {
		return repository.findAll();
	}

	@PostMapping("/employee/add")
	Employee addEmployee(@RequestBody Employee entry, @RequestParam String authkey) {
		if (authkey.equalsIgnoreCase(key)) {
			return repository.save(entry);
		}
		return null;
	}

	
	
	@PutMapping("/employee/update")
	Employee updateEmployee(@RequestBody Employee entry, @RequestParam String email, @RequestParam String authkey) {
		if (authkey.equalsIgnoreCase(key)) {
		Employee emp = repository.findByEmail(email);
			emp.setUsername(entry.getUsername());
			emp.setEmail(entry.getEmail());
			return repository.save(emp);
		}
		return null;
	}

	
	
	@GetMapping("/employee/login")
	Employee employeeLogin(@RequestParam String email, @RequestParam String authkey) {
		if (authkey.equalsIgnoreCase(key)) {
			return repository.findByEmail(email);
		}
		return null;
	}

	
	
	@DeleteMapping("/employee/delete")
	void deleteItem(@RequestParam String email, @RequestParam String authkey) {
		if (authkey.equalsIgnoreCase(key)) {
			Employee employee = repository.findByEmail(email);
			repository.delete(employee);
		}
	}
}
