package com.bill.app.gateway.service;

import com.bill.app.gateway.entity.Customer;
import com.bill.app.gateway.entity.Wallet;
import com.bill.app.gateway.repository.CustomerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @SneakyThrows
    void test_registerCustomer(){
        List<Customer> customerList = new ArrayList<>();
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);
        customerService.registerCustomer(getNewCusotmer());
        verify(customerRepository).findAll();
    }

    private Customer getNewCusotmer(){
        Customer customer = new Customer();
        customer.setEmail("bhamaredeepak@gmail.com");
        Wallet wallet = new Wallet();
        wallet.setBalance(50L);

        customer.setWallet(wallet);
        return customer;
    }
}
