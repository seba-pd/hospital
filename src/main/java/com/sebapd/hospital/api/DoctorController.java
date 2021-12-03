package com.sebapd.hospital.api;

import com.sebapd.hospital.entity.Appointment;
import com.sebapd.hospital.entity.Doctor;
import com.sebapd.hospital.entity.Patient;
import com.sebapd.hospital.service.AppointmentService;
import com.sebapd.hospital.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    public DoctorController(AppointmentService appointmentService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getDoctorsAppointments(Principal principal) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByDoctor(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/add_appointment")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(appointmentService.addAppointment(appointment), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_appointment/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor, Principal principal){
        return new ResponseEntity<>(doctorService.updateDoctor(doctor,principal.getName()), HttpStatus.OK);
    }

}
