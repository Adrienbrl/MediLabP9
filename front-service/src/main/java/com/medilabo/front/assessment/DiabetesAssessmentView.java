package com.medilabo.front.assessment;

public record DiabetesAssessmentView(
        Long patientId,
        String patientName,
        int age,
        int triggerCount,
        String riskLevel,
        String riskLabel
) {
}
