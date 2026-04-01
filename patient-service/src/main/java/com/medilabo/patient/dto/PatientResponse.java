package com.medilabo.patient.dto;

import com.medilabo.patient.patient.Patient;

import java.time.LocalDate;

public record PatientResponse(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String gender,
        String address,
        String phone
) {

    public static PatientResponse from(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getPhone()
        );
    }
}
