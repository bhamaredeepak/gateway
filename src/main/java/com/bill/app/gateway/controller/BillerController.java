package com.bill.app.gateway.controller;

import com.bill.app.gateway.entity.Biller;
import com.bill.app.gateway.service.BillerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class BillerController {

    @Autowired
    private BillerService billerService;

    @GetMapping("/getAllBillers")
    public ResponseEntity<List<Biller>> getAllBillers(){
        log.info("Inside the look up All Billers");
        return new ResponseEntity<List<Biller>>(billerService.getAllBillers(), HttpStatus.OK);
    }
}
