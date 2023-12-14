package com.sf.bl.jpa;

import com.sf.bl.entity.TeOperate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TeOperateRepository extends JpaRepository<TeOperate, Long> {

    @Query("SELECT o FROM TeOperate o WHERE ((o.debitAcc.customer.id = :customerId) or (o.creditAcc.customer.id = :customerId)) and (o.dateAdded between :dateIn and :dateOut) ORDER BY o.id")
    List<TeOperate> findOperatesByCustomerAndPeriod(@Param("customerId") Long customerId,
                                                    @Param("dateIn") LocalDateTime dateIn,
                                                    @Param("dateOut") LocalDateTime dateOut);
    @Query("SELECT o FROM TeOperate o WHERE (o.debitAcc.customer.id = :customerId) or (o.creditAcc.customer.id = :customerId) ORDER BY o.id")
    List<TeOperate> findOperatesByCustomer(@Param("customerId") Long customerId);
}
