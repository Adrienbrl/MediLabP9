package com.medilabo.patient.config;

import com.medilabo.patient.patient.Patient;
import com.medilabo.patient.patient.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadPatients(PatientRepository patientRepository) {
        return args -> {
            if (patientRepository.count() > 0) {
                return;
            }

            List<Patient> patients = List.of(
                    new Patient("Test", "TestNone", LocalDate.parse("1966-12-31"), "F", "1 Brookside St", "100-222-3333"),
                    new Patient("Test", "TestBorderline", LocalDate.parse("1945-06-24"), "M", "2 High St", "200-333-4444"),
                    new Patient("Test", "TestDanger", LocalDate.parse("2004-06-18"), "M", "3 Club Road", "300-444-5555"),
                    new Patient("Test", "TestEarlyOnset", LocalDate.parse("2002-06-28"), "F", "4 Valley Dr", "400-555-6666")
            );

            patientRepository.saveAll(patients);
        };
    }
}
