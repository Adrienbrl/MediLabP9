package com.medilabo.notes.note;

import com.medilabo.notes.dto.PatientNoteCreateRequest;
import com.medilabo.notes.dto.PatientNoteResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PatientNoteService {

    private final PatientNoteRepository patientNoteRepository;

    public PatientNoteService(PatientNoteRepository patientNoteRepository) {
        this.patientNoteRepository = patientNoteRepository;
    }

    public List<PatientNoteResponse> findByPatientId(Long patientId) {
        return patientNoteRepository.findByPatientIdOrderByCreatedAtAsc(patientId)
                .stream()
                .map(PatientNoteResponse::from)
                .toList();
    }

    public PatientNoteResponse create(PatientNoteCreateRequest request) {
        PatientNote note = new PatientNote(
                request.patientId(),
                request.patientName(),
                request.content(),
                Instant.now()
        );

        return PatientNoteResponse.from(patientNoteRepository.save(note));
    }
}
