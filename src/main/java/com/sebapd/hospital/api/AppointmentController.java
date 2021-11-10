package com.sebapd.hospital.api;

import com.sebapd.hospital.entity.Appointment;
import com.sebapd.hospital.repository.AppointmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return new ResponseEntity<>(appointmentRepository.findAll(),HttpStatus.OK);
    }
}
