package com.medilabo.patient.patient;

import com.medilabo.patient.dto.PatientRequest;
import com.medilabo.patient.dto.PatientResponse;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponse> findAll() {
        return patientRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(PatientResponse::from)
                .toList();
    }

    public PatientResponse findById(Long id) {
        return patientRepository.findById(id)
                .map(PatientResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    public PatientResponse create(PatientRequest request) {
        Patient patient = new Patient(
                request.firstName(),
                request.lastName(),
                request.birthDate(),
                request.gender(),
                request.address(),
                request.phone()
        );
        return PatientResponse.from(patientRepository.save(patient));
    }

    public PatientResponse update(Long id, PatientRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));

        patient.setFirstName(request.firstName());
        patient.setLastName(request.lastName());
        patient.setBirthDate(request.birthDate());
        patient.setGender(request.gender());
        patient.setAddress(request.address());
        patient.setPhone(request.phone());

        return PatientResponse.from(patientRepository.save(patient));
    }
}
