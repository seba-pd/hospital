package com.sebapd.hospital.service;

import com.sebapd.hospital.entity.Doctor;
import com.sebapd.hospital.exception.DoctorNotFoundException;
import com.sebapd.hospital.exception.UsernameIsNotFreeException;
import com.sebapd.hospital.repository.DoctorRepository;
import com.sebapd.hospital.security.Authority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        doctor.addRole(Authority.DOCTOR);
        if (doctorRepository.findDoctorByUsername(doctor.getUsername()).isPresent())
            throw new UsernameIsNotFreeException("Username is not free!");
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorByUsername(String username) {
        return doctorRepository.findDoctorByUsername(username)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found!"));
    }

    public Doctor updateDoctor(Doctor doctor,String username) {
        var updateDoctor = doctorRepository.findDoctorByUsername(username);
        if (updateDoctor.isEmpty())
            throw new DoctorNotFoundException("Doctor nor found!");
        else
            doctor.setId(updateDoctor.get().getId());
        return doctorRepository.save(doctor);
    }

    public void deleteDoctorByUsername(String username) {
        doctorRepository.deleteDoctorByUsername(username);
    }

}
