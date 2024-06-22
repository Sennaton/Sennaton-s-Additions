package org.sennaton.sennaton_additions.SennatonMob;

import java.util.Arrays;
import java.util.Comparator;

public enum NynaVariant {
    NYNA(0),
    FRIGID_NYNA(1),
    UN_NYNA(2),
    FIREY_NYNA(3),
    HAUNTED_NYNA(4);

    private static final NynaVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(NynaVariant::getId)).toArray(NynaVariant[]::new);
    private final int id;

    NynaVariant(int id) {
        this.id = id;
    }

    public static NynaVariant get(String string) {
        return NynaVariant.valueOf(string);
    }

    public int getId() {
        return this.id;
    }

    public static NynaVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}