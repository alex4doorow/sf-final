package com.sf.bl.jpa;

import com.sf.bl.entity.TePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TePersonRepository extends JpaRepository<TePerson, Long> {
}
