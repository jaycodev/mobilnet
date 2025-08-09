package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobilnet.dto.DistrictQuantityDTO;
import com.mobilnet.dto.RegistrationByMonthDTO;
import com.mobilnet.model.Ruc10Record;

import java.util.List;

@Repository
public interface Ruc10RecordRepository extends JpaRepository<Ruc10Record, Integer> {
    List<Ruc10Record> findAllByOrderByIdDesc();

    @Query("""
        SELECT r FROM Ruc10Record r
        WHERE (:stateId IS NULL OR r.state.id = :stateId)
        ORDER BY r.id DESC
    """)
    List<Ruc10Record> findAllWithFilter(@Param("stateId") Integer stateId);

    @Query("SELECT COUNT(r) FROM Ruc10Record r WHERE r.state.description = :description")
    long countByStateDescription(@Param("description") String description);

    @Query("""
        SELECT MONTH(s.registrationDate) AS month, COUNT(r) AS quantity
        FROM Ruc10Record r
        JOIN r.schedule s
        WHERE s.registrationDate IS NOT NULL
        GROUP BY MONTH(s.registrationDate)
        ORDER BY month
    """)
    List<RegistrationByMonthDTO> countRegistrationsByMonth();

    @Query("""
        SELECT r.district.name, COUNT(r)
        FROM Ruc10Record r
        GROUP BY r.district.name
        ORDER BY COUNT(r) DESC
    """)
    List<DistrictQuantityDTO> countRegistrationsByDistrict();
}
