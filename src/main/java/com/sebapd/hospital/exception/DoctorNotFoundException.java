package com.sebapd.hospital.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String s) {
        super(s);
    }
}
