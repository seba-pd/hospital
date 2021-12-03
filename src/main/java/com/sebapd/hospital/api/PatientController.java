package com.sebapd.hospital.api;

import com.sebapd.hospital.entity.Appointment;
import com.sebapd.hospital.entity.Patient;
import com.sebapd.hospital.service.AppointmentService;
import com.sebapd.hospital.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.addPatient(patient),HttpStatus.CREATED);
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentByPatient(Principal principal){
        return new ResponseEntity<>(appointmentService.getAppointmentsByPatient(principal.getName()),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, Principal principal){
        return new ResponseEntity<>((patientService.updatePatient(patient, principal.getName())), HttpStatus.OK);
    }
}
