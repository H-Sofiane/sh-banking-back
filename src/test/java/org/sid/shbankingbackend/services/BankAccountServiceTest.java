package org.sid.shbankingbackend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sid.shbankingbackend.dtos.CustomerDTO;
import org.sid.shbankingbackend.entities.Customer;
import org.sid.shbankingbackend.mappers.BankAccountMapperImpl;
import org.sid.shbankingbackend.repositories.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BankAccountServiceTest {

    @InjectMocks
    private BankAccountServiceImpl bankAccountService; // Injecte les mocks dans l'implémentation du service

    @Mock
    private CustomerRepository customerRepository; // Mock manuel du repository

    @Mock
    private BankAccountMapperImpl dtoMapper; // Mock manuel du mapper

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks avant chaque test
    }

    @Test
    public void testSaveCustomer() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John Doe");
        customerDTO.setEmail("john.doe@gmail.com");
        customerDTO.setPhoneNumber("1234567890");

        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@gmail.com");
        customer.setPhoneNumber("1234567890");

        when(dtoMapper.fromCustomerDTO(customerDTO)).thenReturn(customer); // Mock du mapper

        // Act
        bankAccountService.saveCustomer(customerDTO);

        // Assert
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerCaptor.capture());
        Customer savedCustomer = customerCaptor.getValue();

        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("john.doe@gmail.com", savedCustomer.getEmail());
        assertEquals("1234567890", savedCustomer.getPhoneNumber()); // Vérification du numéro de téléphone
    }
}