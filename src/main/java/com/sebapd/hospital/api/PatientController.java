package com.sebapd.hospital.api;

import com.sebapd.hospital.entity.Patient;
import com.sebapd.hospital.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.addPatient(patient),HttpStatus.CREATED);
    }
}
