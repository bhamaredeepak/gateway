package com.bill.app.gateway.service;

import com.bill.app.gateway.entity.Biller;
import com.bill.app.gateway.repository.BillerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class BillerServiceTest {

    @InjectMocks
    private BillerService billerService;

    @Mock
    private BillerRepository billerRepository;

    @Test
    @SneakyThrows
    void test_billerService(){
        Mockito.when(billerRepository.findAll()).thenReturn(new ArrayList<Biller>());
        billerService.getAllBillers();
        verify(billerRepository).findAll();
    }
}
