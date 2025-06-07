package com.ecommerce.customer.service.customerService;

import com.ecommerce.customer.dto.AddressDTO;
import com.ecommerce.customer.dto.CustomerDTO;
import com.ecommerce.customer.entity.Address;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.repository.AddressRepository;
import com.ecommerce.customer.repository.CustomerRepository;
import com.ecommerce.customer.service.AddressService;
import com.ecommerce.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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
    CustomerService customerService;

    // this annotation is used for object creation for those objects which is indirectly dependent to the testing class
    @Mock
    CustomerRepository customerRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    AddressService addressService;


    Customer customer;


    CustomerDTO customerDTO;


    Address address;

    @BeforeEach
    void setUp(){

        //Arrange : create mock data
        customer=new Customer();
        customer.setCustomerId(1L);
        customer.setFirstname("Omraj");
        customer.setLastname("Pradhan");
        customer.setEmail("om@gmail.com");
        customer.setPhoneNumber("45335");

        address=new Address();
        address.setAddressId(101L);
        address.setCity("Kolkata");
        address.setPincode(455331144);
        address.setStreet("Salt lake");

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        customer.setAddresses(addressList);

        customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1L);
        customerDTO.setFirstName("Omraj");
        customerDTO.setLastName("Pradhan");
        customerDTO.setEmail("om@gmail.com");
        customerDTO.setPhoneNumber("45335");

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setAddressId(101L);
        addressDTO1.setCity("Kolkata");
        addressDTO1.setPincode(455331144);
        addressDTO1.setStreet("Salt lake");

        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setAddressId(102L);
        addressDTO2.setCity("Siliguri");
        addressDTO2.setPincode(644665455);
        addressDTO2.setStreet("Batashi");

        List<AddressDTO> addressDTOList = new ArrayList<>();
        addressDTOList.add(addressDTO1);
        addressDTOList.add(addressDTO2);

        customerDTO.setAddresses(addressDTOList);
    }

    @Test
    void save_Customer_Details_Success() {

        // this is stubbing
        //when then => it creates a mock behaviour
        // which means "When this method is called with these parameters, then do this."
        // doNothing() => becoz save method return void, so nothing
        //when(addressRepository.save(any(Address.class))).thenReturn(address);
        // Mocking DB
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        //act : what is the result when this method is called of customer service
        CustomerDTO result = customerService.saveCustomerDetails(customerDTO);

        //assert : assertion is done to verify that the result obtained from the act is same as expected.
        assertEquals(1L,result.getCustomerId());

        // verify that repository is call given number of times
        verify(customerRepository,times(1)).save(any(Customer.class));
    }

    @Test
    void save_Customer_Details_Failure() {

        //act
        CustomerDTO result = customerService.saveCustomerDetails(null);

        //assert
        assertEquals(null,result);

        verify(customerRepository,never()).save(any(Customer.class));
    }

    @Test
    void find_Customer_By_Id_Test_Success(){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Omraj");
        customerDTO.setLastName("Pradhan");
        customerDTO.setEmail("om@gmail.com");
        customerDTO.setPhoneNumber("45335");
        //customerDTO.setAddresses(addressDTOList);

        // stubbing
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        //act
        CustomerDTO customerFound = customerService.findCustomerById(1L);

        //assert
        assertNotNull(customerFound);
        //assertEquals(1L,customerFound.getCustomerId());
        assertEquals("Omraj",customerFound.getFirstName());
        assertEquals("Pradhan",customerFound.getLastName());
        //assertEquals(CustomerDTO.getAddresses().size(),customerFound.getAddresses().size());

        verify(customerRepository, times(1)).findById(1L);

    }

    @Test
    void customer_By_Id_Test_NotFound(){

        //Arrange
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(CustomerNotFoundException.class,
                ()-> customerService.findCustomerById(2L));

        verify(customerRepository, times(1)).findById(2L);

    }

    @Test
    void findAllCustomersTest_success() {

        CustomerDTO CustomerDTO = new CustomerDTO();
        CustomerDTO.setFirstName("Omraj");
        CustomerDTO.setLastName("Pradhan");

        List<CustomerDTO> customerList = new ArrayList<>();
        customerList.add(CustomerDTO);

        when(customerRepository.findAll()).thenReturn(List.of(customer));

        //Act
        List<CustomerDTO> result = customerService.findAllCustomers();

        //Assert
        assertNotNull(result);
        assertEquals(1,result.size());

        verify(customerRepository,times(1)).findAll();
    }
}
