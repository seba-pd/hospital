package com.sebapd.hospital.repository;

import com.sebapd.hospital.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends BaseUserRepository<Patient> {

    Optional<Patient> findByUsername(String username);
    void deleteByUsername(String username);
}
