package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.MainContact;

@Repository
public interface MainContactRepository extends JpaRepository<MainContact, Integer> {
}
