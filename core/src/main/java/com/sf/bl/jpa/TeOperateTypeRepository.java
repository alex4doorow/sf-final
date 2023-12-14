package com.sf.bl.jpa;

import com.sf.bl.entity.TeOperateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeOperateTypeRepository extends JpaRepository<TeOperateType, Long> {

    @Query("SELECT DISTINCT ot FROM TeOperateType ot WHERE ot.code = :code")
    TeOperateType getOperateTypeByCode(@Param("code") String code);
}
