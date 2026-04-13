package com.medilabo.assessment.risk;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TriggerCounterTest {

    private final TriggerCounter triggerCounter = new TriggerCounter();

    @Test
    void countTriggersIgnoresCaseAccentsAndDuplicates() {
        int triggerCount = triggerCounter.countTriggers(List.of(
                "Hémoglobine A1C supérieure. Le patient est fumeur.",
                "Réaction aux médicaments. Le patient fume encore."
        ));

        assertThat(triggerCount).isEqualTo(3);
    }

    @Test
    void countTriggersMatchesEarlyOnsetTestCase() {
        int triggerCount = triggerCounter.countTriggers(List.of(
                "Le patient déclare qu'il lui est devenu difficile de monter les escaliers. "
                        + "Il se plaint également d'être essoufflé. Tests de laboratoire indiquant que les anticorps sont élevés. "
                        + "Réaction aux médicaments.",
                "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps.",
                "Le patient déclare avoir commencé à fumer depuis peu. Hémoglobine A1C supérieure au niveau recommandé.",
                "Taille, Poids, Cholestérol, Vertige et Réaction."
        ));

        assertThat(triggerCount).isEqualTo(8);
    }
}
