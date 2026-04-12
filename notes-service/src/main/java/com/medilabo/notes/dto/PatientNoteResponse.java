package com.medilabo.notes.dto;

import com.medilabo.notes.note.PatientNote;

import java.time.Instant;

public record PatientNoteResponse(
        String id,
        Long patientId,
        String patientName,
        String content,
        Instant createdAt
) {

    public static PatientNoteResponse from(PatientNote note) {
        return new PatientNoteResponse(
                note.getId(),
                note.getPatientId(),
                note.getPatientName(),
                note.getContent(),
                note.getCreatedAt()
        );
    }
}
