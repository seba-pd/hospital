package com.sebapd.hospital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = DoctorNotFoundException.class)
    public ResponseEntity<?> doctorNotFoundExceptionHandler(DoctorNotFoundException doctorNotFoundException){
        return new ResponseEntity<>(doctorNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PatientNotFoundException.class)
    public ResponseEntity<?> PatientNotFoundExceptionHandler(PatientNotFoundException patientNotFoundException){
        return new ResponseEntity<>(patientNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UsernameIsNotFreeException.class)
    public ResponseEntity<?> usernameIsNotFreeExceptionHandler(UsernameIsNotFreeException usernameIsNotFreeException){
        return new ResponseEntity<>(usernameIsNotFreeException.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
