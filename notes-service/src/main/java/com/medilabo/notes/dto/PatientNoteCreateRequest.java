package com.medilabo.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientNoteCreateRequest(
        @NotNull Long patientId,
        @NotBlank String patientName,
        @NotBlank String content
) {
}
