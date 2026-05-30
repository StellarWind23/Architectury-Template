package com.github.stellarwind23.template.util;

import com.github.stellarwind23.template.init.TemplateInit;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;

public record MTranslation(String prefix, String name) {

    public static final List<MTranslation> MOD_TRANSLATIONS = new ArrayList<>();

    public MTranslation {
        MOD_TRANSLATIONS.add(this);
    }

    public Pair<String, String> createTranslation() {
        var splitName = name.split("_");
        String[] t = new String[splitName.length];

        for(int i = 0; i < splitName.length; i++) {
            t[i] = (splitName[i].substring(0, 1).toUpperCase()) + splitName[i].substring(1);
        }

        return new Pair<>(prefix + "." + TemplateInit.MOD_ID + "." + name, String.join(" ", t));
    }
}
