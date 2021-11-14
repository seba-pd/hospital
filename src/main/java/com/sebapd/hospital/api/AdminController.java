package com.sebapd.hospital.api;

import com.sebapd.hospital.entity.Appointment;
import com.sebapd.hospital.entity.Doctor;
import com.sebapd.hospital.entity.Patient;
import com.sebapd.hospital.service.AppointmentService;
import com.sebapd.hospital.service.DoctorService;
import com.sebapd.hospital.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    public AdminController(PatientService patientService, AppointmentService appointmentService, DoctorService doctorService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @GetMapping("/patients/all")
    public ResponseEntity<List<Patient>> getAllPatients(){
        return new ResponseEntity<>(patientService.getAllPatient(), HttpStatus.OK);
    }

    @GetMapping("/patients/{username}")
    public ResponseEntity<Patient> getPatientByUsername(@PathVariable String username){
        return new ResponseEntity<>(patientService.getPatientByUsername(username),HttpStatus.OK);
    }

    @DeleteMapping("/patients/{username}")
    public void deletePatient(@PathVariable String username){
        patientService.deletePatientByUsername(username);
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return new ResponseEntity<>(appointmentService.getAllAppointments(), HttpStatus.OK);
    }

    @GetMapping("/doctors/all")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return new ResponseEntity<>(doctorService.getAllDoctor(),HttpStatus.OK);
    }

    @GetMapping("/doctors/{username}")
    public ResponseEntity<Doctor> getDoctorByUsername(@PathVariable String username){
        return new ResponseEntity<>(doctorService.getDoctorByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/doctors/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        return new ResponseEntity<>(doctorService.addDoctor(doctor), HttpStatus.CREATED);
    }

    @DeleteMapping("/doctors/{username}")
    public void deleteDoctorByUsername (@PathVariable String username){
        doctorService.deleteDoctorByUsername(username);
    }
}
