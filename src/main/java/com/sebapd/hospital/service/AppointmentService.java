package com.sebapd.hospital.service;

import com.sebapd.hospital.entity.Appointment;
import com.sebapd.hospital.exception.AppointmentNotFoundException;
import com.sebapd.hospital.exception.DoctorNotFoundException;
import com.sebapd.hospital.exception.PatientNotFoundException;
import com.sebapd.hospital.repository.AppointmentRepository;
import com.sebapd.hospital.repository.DoctorRepository;
import com.sebapd.hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id){
        return appointmentRepository.findById(id).
                orElseThrow(() ->new AppointmentNotFoundException("Appointment not found!"));
    }

    public List<Appointment> getAppointmentsByPatient(String username){
        var patient = patientRepository.findByUsername(username)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found!"));
        return patient.getAppointments();
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
