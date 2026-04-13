package com.medilabo.assessment.dto;

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
}
