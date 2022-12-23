package com.bill.app.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @NotBlank(message = "email should not be blank")
    private String email;

    /*@OneToMany(targetEntity = Biller.class, cascade = CascadeType.ALL)
    //@JoinColumn(name = "cb_fk", referencedColumnName = "id")
    private List<Biller> billers;*/
    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;
}
