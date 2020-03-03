package com.yams.userapiv2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long>{

	Guest findByEmail(String email);

}
