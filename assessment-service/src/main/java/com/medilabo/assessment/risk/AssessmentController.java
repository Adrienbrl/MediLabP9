package com.medilabo.assessment.risk;

import com.medilabo.assessment.dto.DiabetesAssessmentResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/patient/{patientId}")
    public DiabetesAssessmentResponse assessPatient(@PathVariable("patientId") Long patientId) {
        return assessmentService.assessPatient(patientId);
    }
}
