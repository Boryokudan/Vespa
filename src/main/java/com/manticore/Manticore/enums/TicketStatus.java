package com.manticore.Manticore.enums;

public enum TicketStatus {
    PENDING("Pending"),
    ASSIGNED("Assigned"),
    IN_PROGRESS("In progress"),
    CLOSED("Closed");

    private final String stringValue;

    TicketStatus(final String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
