package com.medilabo.front.assessment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "diabetes-assessment-client", url = "${medilab.assessment-service.base-url}")
public interface DiabetesAssessmentClient {

    @GetMapping("/api/assessments/patient/{patientId}")
    DiabetesAssessmentView assessPatient(@PathVariable("patientId") Long patientId);
}
