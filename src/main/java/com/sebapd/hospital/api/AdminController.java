package com.sebapd.hospital.api;

import com.sebapd.hospital.entity.Patient;
import com.sebapd.hospital.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/admin")
public class AdminController {

    private final PatientService patientService;

    public AdminController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients(){
        return new ResponseEntity<>(patientService.getAllPatient(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Patient> getPatientByUsername(@PathVariable String username){
        return new ResponseEntity<>(patientService.getPatientByUsername(username),HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public void deletePatient(@PathVariable String username){
        patientService.deletePatientByUsername(username);
    }
}
