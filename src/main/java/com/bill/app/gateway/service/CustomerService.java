package com.bill.app.gateway.service;

import com.bill.app.gateway.entity.Customer;
import com.bill.app.gateway.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer){
        try{
            List<Customer> customersList = customerRepository.findAll();
            Customer existCustomer = customersList.stream().filter(customer1 -> customer1.getEmail().equals(customer.getEmail()))
                    .findAny()
                    .orElse(null);
            if(existCustomer != null){
                log.error("Customer is already register with given email: {}",customer.getEmail());
                throw new RuntimeException("Customer Already register with given email id "+ customer.getEmail());
            } else {
                return customerRepository.save(customer);
            }
        } catch (Exception e){
            throw e;
        }
    }

    public Customer addFundToWallet(Long fund, String customerEmailId){
        List<Customer> customersList = customerRepository.findAll();
        Customer existCustomer = customersList.stream().filter(customer1 -> customer1.getEmail().equals(customerEmailId))
                .findAny()
                .orElse(null);
        if(null != existCustomer){
            Long totalFund = existCustomer.getWallet().getBalance()+fund;
            existCustomer.getWallet().setBalance(totalFund);
            return customerRepository.save(existCustomer);
        } else
            log.error("Customer is not found in Customer table {}", customerEmailId);
            throw new RuntimeException("Customer with given email id: "+ customerEmailId + " Not Found");
    }
}
