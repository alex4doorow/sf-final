package com.sf.bl.jpa;

import com.sf.bl.entity.TeCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeCurrencyRepository extends JpaRepository<TeCurrency, Long> {

    @Query("SELECT DISTINCT c FROM TeCurrency c WHERE c.code = :code")
    TeCurrency getCurrencyByCode(@Param("code") String code);
}
