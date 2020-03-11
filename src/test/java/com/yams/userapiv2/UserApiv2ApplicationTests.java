package com.yams.userapiv2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApiv2ApplicationTests {

	@Autowired
    private CustomerController cc;
	
	@Autowired
    private GuestController gc;

	
	@Autowired
    private EmployeeController ec;
	
    
	@Test
	void alwaysTrue() {
	    assertThat(1==1).isEqualTo(true);
	}
	
	@Test
	void alwaysFalse() {
	    assertThat(1==0).isEqualTo(false);
	}
	
	@Test
	void contextLoads() {
	    assertThat(cc).isNotNull();
	}

	@Test
	void allCustomersReturnsNonEmptyList() {
	    assertThat(cc.allItems())
	        .isNotNull()
	        .isNotEmpty();
	}
	
	@Test
	void allGuestsReturnsNonEmptyList() {
	    assertThat(gc.allItems())
	        .isNotNull()
	        .isNotEmpty();
	}
	
	@Test
	void allEmployeesReturnsNonEmptyList() {
	    assertThat(ec.allItems())
	        .isNotNull()
	        .isNotEmpty();
	}
	
	@Test
	void customerLoginMethodReturnsEmptyListForNonEntry() {
	    assertThat(cc.customerLogin("This is not a real email, so this test should return null"))
	        .isNull();
	}
	
	@Test
	void employeeLoginMethodReturnsEmptyListForNonEntry() {
	    assertThat(ec.employeeLogin("This is not a real email, so this test should return null", 
	    							"this is not a real auth string... so this should return null"))
	        .isNull();
	}
	
	
	@Test
	void addCustomerThenRemoveCustomerDoesntAffectGuestListSize() {
	    Customer customer = new Customer("JunitTestUsername","junitTest@gmail.com");
		int originalSize = cc.allItems().size();
		
		assertThat(cc.addCustomer(customer))
        .isNotNull()
        .isEqualTo(customer);
	    
	    assertThat(cc.customerLogin("junitTest@gmail.com"))
	        .isNotNull()
	        .isEqualTo(customer);
	    
	    assertThat(cc.allItems())
	        .size().isGreaterThan(originalSize);
	    
	    cc.deleteItem(customer.getId());
	    
	    assertThat(cc.allItems())
	        .isNotNull()
	        .size().isEqualTo(originalSize);
	}


}
