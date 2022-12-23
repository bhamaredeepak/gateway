package com.bill.app.gateway.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Biller {
    @Id
    private Long id;

    @Column(name = "balance")
    @JsonProperty
    private Long balance;

    @Column(name = "name")
    @JsonProperty
    private String name;
}
