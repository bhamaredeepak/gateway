package com.bill.app.gateway.repository;

import com.bill.app.gateway.entity.Billerdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerDataRepository extends JpaRepository<Billerdata, Long> {
}
