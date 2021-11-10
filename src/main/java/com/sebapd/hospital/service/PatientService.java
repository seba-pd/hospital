package com.sebapd.hospital.service;

import com.sebapd.hospital.entity.Patient;
import com.sebapd.hospital.exception.PatientNotFoundException;
import com.sebapd.hospital.exception.UsernameIsNotFreeException;
import com.sebapd.hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }

    public Patient getPatientByUsername(String username){
        return patientRepository.findByUsername(username)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
    }

    public void deletePatientByUsername(String username){
        patientRepository.deleteByUsername(username);
    }

    public Patient addPatient(Patient patient){
        if(patientRepository.findByUsername(patient.getUsername()).isPresent()) throw new UsernameIsNotFreeException("Username is not free!");
        return patientRepository.save(patient);
    }

}
