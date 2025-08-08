package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.ContactoPrincipal;

@Repository
public interface ContactoPrimarioRepository extends JpaRepository<ContactoPrincipal, Integer> {
}
