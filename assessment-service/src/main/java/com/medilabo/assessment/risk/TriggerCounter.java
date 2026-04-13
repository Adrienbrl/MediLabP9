package com.medilabo.assessment.risk;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class TriggerCounter {

    private static final List<Pattern> TRIGGER_PATTERNS = List.of(
            Pattern.compile("\\bhemoglobine a1c\\b"),
            Pattern.compile("\\bmicroalbumine\\b"),
            Pattern.compile("\\btaille\\b"),
            Pattern.compile("\\bpoids\\b"),
            Pattern.compile("\\bfum\\w*\\b"),
            Pattern.compile("\\banormal\\w*\\b"),
            Pattern.compile("\\bcholesterol\\b"),
            Pattern.compile("\\bvertige\\w*\\b"),
            Pattern.compile("\\brechute\\w*\\b"),
            Pattern.compile("\\breaction\\w*\\b"),
            Pattern.compile("\\banticorps\\b")
    );

    public int countTriggers(List<String> notes) {
        String normalizedNotes = normalize(String.join(" ", notes));

        return (int) TRIGGER_PATTERNS.stream()
                .filter(pattern -> pattern.matcher(normalizedNotes).find())
                .count();
    }

    private String normalize(String value) {
        String withoutAccents = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return withoutAccents.toLowerCase(Locale.ROOT);
    }
}
