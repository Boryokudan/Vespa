package com.manticore.Manticore.enums;

public enum Grade {
    INTERN("Intern"),
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior");

    private final String stringValue;

    Grade(final String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
