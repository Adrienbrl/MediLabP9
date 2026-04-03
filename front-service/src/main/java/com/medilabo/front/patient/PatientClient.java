package com.medilabo.front.patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "patient-client", url = "${medilab.patient-service.base-url}")
public interface PatientClient {

    @GetMapping("/api/patients")
    List<PatientView> getPatients();

    @GetMapping("/api/patients/{id}")
    PatientView getPatient(@PathVariable("id") Long id);

    @PostMapping("/api/patients")
    PatientView createPatient(@RequestBody PatientForm patientForm);

    @PutMapping("/api/patients/{id}")
    PatientView updatePatient(@PathVariable("id") Long id, @RequestBody PatientForm patientForm);
}
