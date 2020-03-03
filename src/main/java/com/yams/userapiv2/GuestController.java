package com.yams.userapiv2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {

	public  String key = "1234567";
	
	@Autowired
	GuestRepository guestrepository;

	
	//For development purposes
	@GetMapping("/guest")
	List<Guest> allItems() {
		return guestrepository.findAll();
	}
	
	
	
	@PostMapping("/guest/login")
	public Guest guestLogin(@RequestParam String email) {
			Guest guest = new Guest(email);
			return guestrepository.save(guest);
	}

	
	
	@DeleteMapping("/guest/delete")
	void deleteItem(@RequestParam String email, @RequestParam String authkey) {
		if(authkey.equalsIgnoreCase(key)) {
		Guest guest = guestrepository.findByEmail(email);
		guestrepository.delete(guest);
		}
	}

}
