package com.medilabo.front.note;

import java.time.Instant;

public record PatientNoteView(
        String id,
        Long patientId,
        String patientName,
        String content,
        Instant createdAt
) {
}
