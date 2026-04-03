package com.medilabo.front.patient;

import java.time.LocalDate;

public record PatientView(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String gender,
        String address,
        String phone
) {
}
