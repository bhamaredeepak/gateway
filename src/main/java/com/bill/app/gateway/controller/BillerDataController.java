package com.bill.app.gateway.controller;

import com.bill.app.gateway.entity.Billerdata;
import com.bill.app.gateway.repository.BillerRepository;
import com.bill.app.gateway.service.BillerDataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class BillerDataController {

    @Autowired
    private BillerDataService billerDataService;
    @Autowired
    private BillerRepository billerRepository;

    @GetMapping("/getAllBillerData")
    public List<Billerdata> getAllBillerData(){
        log.info("Get Customers biller pending data");
        return billerDataService.getAllBillerData();
    }

    @GetMapping("/getCustomerBillerData")
    public ResponseEntity<?>  getCustomerBillerData(@RequestParam(name = "email") String email){
        try{
            log.info("Get Customer biller pending data for {}",email);
            return new ResponseEntity<List<Billerdata>>(billerDataService.getCustomerBillerData(email), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pay/yourdue/payment")
    public ResponseEntity<?> payYourDuePayment(@RequestParam(name = "billername") String billername, @RequestParam(name = "email") String email, @RequestParam(name = "monthBill") String monthBill){

        try {
            log.info("Inside the paying due for customer: {}, billername: {},  monthbill: {}",email, billername, monthBill);
            String status = billerDataService.payYourDuePayment(billername, email, monthBill);
            log.info("Due payment for customer {} is completed successfully", email);
            return new ResponseEntity<String>(status, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
