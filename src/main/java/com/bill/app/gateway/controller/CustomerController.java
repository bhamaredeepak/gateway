package com.bill.app.gateway.controller;

import com.bill.app.gateway.entity.Customer;
import com.bill.app.gateway.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/registerCustomer")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        log.info("Inside to register customer: {}", customer.getEmail());
        if(result.hasErrors()){
            Map<String,String> errorsMap = new HashMap<>();
            for(FieldError fieldError: result.getFieldErrors()){
                errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorsMap, HttpStatus.BAD_REQUEST);
        }
        try{
            Customer customer1 = customerService.registerCustomer(customer);
            log.info("Customer {} registered successfully", customer1.getEmail());
            return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/addFundToWallet")
    public ResponseEntity<?> addFundToWallet(@RequestParam(name = "email", required = true) String email, @RequestParam(name = "fund", required = true) Long fund){
        try{
            log.info("Inside the add fund to wallet for customer {}", email);
            return new ResponseEntity<Customer>(customerService.addFundToWallet(fund,email), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
