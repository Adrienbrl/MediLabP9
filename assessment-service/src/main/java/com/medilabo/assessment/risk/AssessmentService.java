package com.medilabo.assessment.risk;

import com.medilabo.assessment.client.PatientClient;
import com.medilabo.assessment.client.PatientNoteClient;
import com.medilabo.assessment.dto.DiabetesAssessmentResponse;
import com.medilabo.assessment.dto.PatientNoteResponse;
import com.medilabo.assessment.dto.PatientResponse;
import com.medilabo.assessment.dto.RiskLevel;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AssessmentService {

    private final PatientClient patientClient;
    private final PatientNoteClient patientNoteClient;
    private final TriggerCounter triggerCounter;
    private final Clock clock;

    public AssessmentService(
            PatientClient patientClient,
            PatientNoteClient patientNoteClient,
            TriggerCounter triggerCounter,
            Clock clock
    ) {
        this.patientClient = patientClient;
        this.patientNoteClient = patientNoteClient;
        this.triggerCounter = triggerCounter;
        this.clock = clock;
    }

    public DiabetesAssessmentResponse assessPatient(Long patientId) {
        PatientResponse patient = patientClient.getPatient(patientId);
        List<PatientNoteResponse> notes = patientNoteClient.getNotesByPatientId(patientId);

        int age = Period.between(patient.birthDate(), LocalDate.now(clock)).getYears();
        int triggerCount = triggerCounter.countTriggers(notes.stream()
                .map(PatientNoteResponse::content)
                .toList());
        RiskLevel riskLevel = determineRisk(patient.gender(), age, triggerCount);

        return new DiabetesAssessmentResponse(
                patient.id(),
                patient.firstName() + " " + patient.lastName(),
                age,
                triggerCount,
                riskLevel,
                riskLevel.getLabel()
        );
    }

    private RiskLevel determineRisk(String gender, int age, int triggerCount) {
        if (triggerCount == 0) {
            return RiskLevel.NONE;
        }

        if (age > 30) {
            if (triggerCount >= 8) {
                return RiskLevel.EARLY_ONSET;
            }
            if (triggerCount >= 6) {
                return RiskLevel.IN_DANGER;
            }
            if (triggerCount >= 2) {
                return RiskLevel.BORDERLINE;
            }
            return RiskLevel.NONE;
        }

        if ("M".equalsIgnoreCase(gender)) {
            if (triggerCount >= 5) {
                return RiskLevel.EARLY_ONSET;
            }
            if (triggerCount >= 3) {
                return RiskLevel.IN_DANGER;
            }
            return RiskLevel.NONE;
        }

        if (triggerCount >= 7) {
            return RiskLevel.EARLY_ONSET;
        }
        if (triggerCount >= 4) {
            return RiskLevel.IN_DANGER;
        }
        return RiskLevel.NONE;
    }
}
