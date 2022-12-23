package com.bill.app.gateway.service;

import com.bill.app.gateway.entity.Biller;
import com.bill.app.gateway.repository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillerService {
    @Autowired
    private BillerRepository billerRepository;

    public List<Biller> getAllBillers(){
        return billerRepository.findAll();
    }
}
