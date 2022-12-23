package com.bill.app.gateway.service;

import com.bill.app.gateway.entity.Biller;
import com.bill.app.gateway.entity.Billerdata;
import com.bill.app.gateway.entity.Customer;
import com.bill.app.gateway.repository.BillerDataRepository;
import com.bill.app.gateway.repository.BillerRepository;
import com.bill.app.gateway.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class BillerDataService {
    @Autowired
    private BillerDataRepository billerDataRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BillerRepository billerRepository;

    public List<Billerdata> getAllBillerData(){
        return billerDataRepository.findAll();
    }

    public List<Billerdata> getCustomerBillerData(String email){
        try {
            List<Billerdata> billerData = billerDataRepository.findAll().stream().filter(billerdata -> billerdata.getCustomerEmail().equals(email))
                    .collect(Collectors.toList());

            if(billerData.size() > 0)
                return billerData;
            else{
                log.error("Customer is not register: {}",email);
                throw new RuntimeException("Customer not found in billerdata register first with email:"+ email);
            }
        }catch (Exception e){
            throw  e;
        }
    }

    public String payYourDuePayment(String billername, String email, String monthBill){

            Customer customer = customerRepository.findAll().stream()
                    .filter(tablecustomer -> tablecustomer.getEmail().equals(email))
                    .findAny()
                    .orElse(null);
            if(null == customer){
                log.error("This customer is not register customer, register first with email:{}", email);
                throw new RuntimeException("This customer is not register customer, register first with email:"+ email);
            }

            Billerdata customerBillerData = billerDataRepository.findAll().stream().
                    filter(billerdata -> billerdata.getCustomerEmail().equals(email) && billerdata.getMonthBill().equals(monthBill) && billerdata.getBillername().equals(billername))
                    .findAny()
                    .orElse(null);

            if(null == customerBillerData){
                log.error("Customer not found in biller data {}", email);
                throw new RuntimeException("Customer not found in billerdata email:+"+email+" month:"+monthBill+" billname:"+billername);
            }

            Optional<Biller> biller = billerRepository.findById(customerBillerData.getBillerId());

            if(customer.getWallet().getBalance() < customerBillerData.getDueAmount())
                throw  new RuntimeException("Customer has lower balance in wallet Email: "+email);

            // update customer wallet first
            Long totalCustomerBalance = customer.getWallet().getBalance() - customerBillerData.getDueAmount();
            customer.getWallet().setBalance(totalCustomerBalance);
            customerRepository.save(customer);

            // update biller balance amount after successfull payment
            Long totalBillerBalance = biller.get().getBalance()+customerBillerData.getDueAmount();
            biller.get().setBalance(totalBillerBalance);
            billerRepository.save(biller.get());

            // update or delete entry from biller data for Customer
            billerDataRepository.delete(customerBillerData);

            return "Success";
    }
}
