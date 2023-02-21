package com.manticore.Manticore.enums;

public enum ProjectStatus {
    INACTIVE("Inactive"),
    ONGOING("Ongoing"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String stringValue;

    ProjectStatus(final String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
