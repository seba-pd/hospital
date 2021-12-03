package com.sebapd.hospital.service;

import com.sebapd.hospital.entity.Patient;
import com.sebapd.hospital.exception.PatientNotFoundException;
import com.sebapd.hospital.exception.UsernameIsNotFreeException;
import com.sebapd.hospital.repository.PatientRepository;
import com.sebapd.hospital.security.Authority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public Patient getPatientByUsername(String username) {
        return patientRepository.findByUsername(username)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
    }

    public Patient addPatient(Patient patient) {
        if (patientRepository.findByUsername(patient.getUsername()).isPresent())
            throw new UsernameIsNotFreeException("Username is not free!");
        patient.setRoles(Set.of(Authority.PATIENT.name()));
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient, String username) {
        var updateDoctor = patientRepository.findByUsername(username);
        if (updateDoctor.isEmpty())
            throw new PatientNotFoundException("Patient nor found!");
        else
            patient.setId(updateDoctor.get().getId());
        return patientRepository.save(patient);
    }

    public void deletePatientByUsername(String username) {
        patientRepository.deleteByUsername(username);
    }
}
