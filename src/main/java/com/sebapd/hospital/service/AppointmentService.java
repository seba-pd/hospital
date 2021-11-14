package com.sebapd.hospital.service;

import com.sebapd.hospital.entity.Appointment;
import com.sebapd.hospital.exception.DoctorNotFoundException;
import com.sebapd.hospital.repository.AppointmentRepository;
import com.sebapd.hospital.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDoctor(String username){
        var doctor = doctorRepository.getDoctorByUsername(username)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found!"));
        return doctor.getAppointments();
    }

    public Appointment addAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }
}
