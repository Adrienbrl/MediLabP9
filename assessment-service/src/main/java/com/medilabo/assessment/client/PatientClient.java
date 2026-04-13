package com.medilabo.assessment.client;

import com.medilabo.assessment.dto.PatientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "assessment-patient-client", url = "${medilab.services.patient-base-url}")
public interface PatientClient {

    @GetMapping("/api/patients/{id}")
    PatientResponse getPatient(@PathVariable("id") Long id);
}
