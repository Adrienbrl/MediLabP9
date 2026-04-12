package com.medilabo.notes.config;

import com.medilabo.notes.note.PatientNote;
import com.medilabo.notes.note.PatientNoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadNotes(PatientNoteRepository patientNoteRepository) {
        return args -> {
            if (patientNoteRepository.count() > 0) {
                return;
            }

            List<PatientNote> notes = List.of(
                    new PatientNote(1L, "TestNone", "Le patient déclare qu'il \"se sent très bien\". Poids égal ou inférieur au poids recommandé.", Instant.parse("2026-01-01T08:00:00Z")),

                    new PatientNote(2L, "TestBorderline", "Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement.", Instant.parse("2026-01-02T08:00:00Z")),
                    new PatientNote(2L, "TestBorderline", "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois. Il remarque également que son audition continue d'être anormale.", Instant.parse("2026-01-03T08:00:00Z")),

                    new PatientNote(3L, "TestDanger", "Le patient déclare qu'il fume depuis peu.", Instant.parse("2026-01-04T08:00:00Z")),
                    new PatientNote(3L, "TestDanger", "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière. Il se plaint également de crises d'apnée respiratoire anormales. Tests de laboratoire indiquant un taux de cholestérol LDL élevé.", Instant.parse("2026-01-05T08:00:00Z")),

                    new PatientNote(4L, "TestEarlyOnset", "Le patient déclare qu'il lui est devenu difficile de monter les escaliers. Il se plaint également d'être essoufflé. Tests de laboratoire indiquant que les anticorps sont élevés. Réaction aux médicaments.", Instant.parse("2026-01-06T08:00:00Z")),
                    new PatientNote(4L, "TestEarlyOnset", "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps.", Instant.parse("2026-01-07T08:00:00Z")),
                    new PatientNote(4L, "TestEarlyOnset", "Le patient déclare avoir commencé à fumer depuis peu. Hémoglobine A1C supérieure au niveau recommandé.", Instant.parse("2026-01-08T08:00:00Z")),
                    new PatientNote(4L, "TestEarlyOnset", "Taille, Poids, Cholestérol, Vertige et Réaction.", Instant.parse("2026-01-09T08:00:00Z"))
            );

            patientNoteRepository.saveAll(notes);
        };
    }
}
