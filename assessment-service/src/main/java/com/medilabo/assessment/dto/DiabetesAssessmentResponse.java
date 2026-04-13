package com.medilabo.assessment.dto;

public record DiabetesAssessmentResponse(
        Long patientId,
        String patientName,
        int age,
        int triggerCount,
        RiskLevel riskLevel,
        String riskLabel
) {
}
