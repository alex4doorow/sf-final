package com.sf.bl.jpa;

import com.sf.bl.entity.TeBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeBalanceRepository extends JpaRepository<TeBalance, Long> {

    @Query("SELECT b FROM TeBalance b WHERE (b.customer.id = :customerId) ORDER BY b.id")
    List<TeBalance> findBalancesByCustomer(@Param("customerId") Long customerId);
}
