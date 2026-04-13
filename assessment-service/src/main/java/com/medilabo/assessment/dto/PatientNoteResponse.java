package com.medilabo.assessment.dto;

import java.time.Instant;

public record PatientNoteResponse(
        String id,
        Long patientId,
        String patientName,
        String content,
        Instant createdAt
) {
}
