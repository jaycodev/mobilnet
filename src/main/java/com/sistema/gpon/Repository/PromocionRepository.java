package com.sistema.gpon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Promocion;
import org.springframework.stereotype.Repository;

@Repository

public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
}
