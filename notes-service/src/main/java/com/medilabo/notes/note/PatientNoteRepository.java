package com.medilabo.notes.note;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientNoteRepository extends MongoRepository<PatientNote, String> {

    List<PatientNote> findByPatientIdOrderByCreatedAtAsc(Long patientId);
}
