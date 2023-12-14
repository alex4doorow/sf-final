package com.sf.bl.jpa;

import com.sf.bl.entity.TeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeCustomerRepository extends JpaRepository<TeCustomer, Long> {
}
