package com.bill.app.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Billerdata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private Long billerId;

    private String billername;

    private String customerEmail;

    private Long dueAmount;

    private String monthBill;

}
