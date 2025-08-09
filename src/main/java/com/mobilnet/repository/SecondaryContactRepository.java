package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.SecondaryContact;

@Repository
public interface SecondaryContactRepository extends JpaRepository<SecondaryContact, Integer> {
}
