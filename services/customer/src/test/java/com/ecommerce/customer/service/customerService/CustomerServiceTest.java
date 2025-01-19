package com.ecommerce.customer.service.customerService;

import com.ecommerce.customer.entity.Address;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.repository.CustomerRepository;
import com.ecommerce.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author omraj
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    // this annotation is used for those objects which is directly dependent to the testing class
    // in order words - this annotation is used for the class whose method we want to test
    @InjectMocks
    private CustomerService customerService;

    // this annotation is used for those objects which is indirectly dependent to the testing class
    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;
    
    private Address address;

    @BeforeEach
    void setUp(){
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstname("Omraj");
        customer.setLastname("Pradhan");
        customer.setEmail("om@gmail.com");
        customer.setPhoneNumber("45335");

        address = new Address();
        address.setAddressId(101L);
        address.setCity("Kolkata");
        address.setPincode(455331144L);
        address.setStreet("Salt lake");
        address.getCustomers().add(customer);
        customer.getAddresses().add(address);
    }

    @Test
    void createCustomerAccount_Success() {

        //mock data
//        Customer customer = new Customer();
//        customer.setFirstname("Omraj");
//        customer.setLastname("Pradhan");
//        customer.setEmail("om@gmail.com");
//        customer.setPhoneNumber("45335");
//        Address address = new Address();
//        address.setCity("Kolkata");
//        address.setPincode(455331144L);
//        address.setStreet("Salt lake");
//        address.getCustomers().add(customer);
//        customer.getAddresses().add(address);

        // this is stubbing
        //when then => it creates a mock behaviour
        // doNothing() => becoz save method return void, so nothing
        doNothing().when(customerRepository.save(customer));

        //act
        String result = customerService.createCustomerAccount(customer);

        //assert
        assertEquals("Saved Successfully",result);

        // verify that repository is call given number of times
        verify(customerRepository,times(1)).save(customer);
    }

    @Test
    void createCustomerAccount_NullCustomer() {

        //act
        String result = customerService.createCustomerAccount(null);

        //assert
        assertEquals("Customer is null",result);

        verify(customerRepository,never()).save(any(Customer.class));
    }

    @Test
    void findCustomerByIdTest_Success(){

        // stubbing
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        //act
        Customer customerFound = customerService.findCustomerById(1L);

        //assert
        assertNotNull(customerFound);
        assertEquals(1L,customerFound.getCustomerId());
        assertEquals("Omraj",customerFound.getFirstname());
        assertEquals("Pradhan",customerFound.getLastname());

        verify(customerRepository, times(1)).findById(1L);

    }

    @Test
    void findCustomerByIdTest_CustomerNotFound(){

        // stubbing
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        //act
        Customer customerFound = customerService.findCustomerById(1L);

        //assert
        Exception exception = assertThrows(CustomerNotFoundException.class,
                ()-> customerService.findCustomerById(2L));

        assertEquals("Customer not found!!",exception.getMessage());

        verify(customerRepository, times(1)).findById(1L);

    }
}
