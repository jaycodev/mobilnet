package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    List<Client> findAllByOrderByDniDesc();

    @Query("""
        SELECT c FROM Client c
        WHERE (:isActive IS NULL OR c.isActive = :isActive)
        ORDER BY c.dni DESC
    """)
    List<Client> findAllWithFilter(@Param("isActive") Boolean isActive);
}
