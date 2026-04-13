package com.medilabo.assessment.client;

import com.medilabo.assessment.dto.PatientNoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "assessment-note-client", url = "${medilab.services.notes-base-url}")
public interface PatientNoteClient {

    @GetMapping("/api/notes/patient/{patientId}")
    List<PatientNoteResponse> getNotesByPatientId(@PathVariable("patientId") Long patientId);
}
