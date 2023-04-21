package com.mango.customer.repository;

import com.mango.customer.model.SloganEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SloganRepository extends JpaRepository<SloganEntity, Long> {
}