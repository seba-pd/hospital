package com.sebapd.hospital.repository;

import com.sebapd.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> getDoctorByUsername(String s);
    void deleteDoctorByUsername(String s);
}
