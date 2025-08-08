package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.ContactoSecundario;

@Repository
public interface ContactoSecundarioRepository extends JpaRepository<ContactoSecundario, Integer> {
}
