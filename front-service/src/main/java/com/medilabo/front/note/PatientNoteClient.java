package com.medilabo.front.note;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "patient-note-client", url = "${medilab.notes-service.base-url}")
public interface PatientNoteClient {

    @GetMapping("/api/notes/patient/{patientId}")
    List<PatientNoteView> getNotesByPatientId(@PathVariable("patientId") Long patientId);

    @PostMapping("/api/notes")
    PatientNoteView createNote(@RequestBody PatientNoteForm patientNoteForm);
}
