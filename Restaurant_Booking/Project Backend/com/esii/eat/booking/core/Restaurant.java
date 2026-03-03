package com.esii.eat.booking.core;

public class Restaurant {
    private final int additionalTables = 3;
    private int availableTables;
    private int currentTableIndex;
    private String name;
    private Table[] tables;
    private int totalTables;

    public Restaurant(String name, int totalTables) {
        this.name = name;
        this.totalTables = totalTables;
        this.availableTables = totalTables + additionalTables;
        this.tables = new Table[availableTables];
        this.currentTableIndex = 0;

        for (int i = 0; i < tables.length; i++) {
            tables[i] = new Table();
        }
    }

    public String getName() {
        return name;
    }

    public boolean hasAvailableTables(int numberOfPeople) {
        double neededTables = Math.ceil(numberOfPeople / 6.0);
        if (neededTables > availableTables) return false;
        if (numberOfPeople < 2) return false;
        if ((currentTableIndex >= totalTables) && (neededTables == 1)) return false;
        return true;
    }

    public boolean reserveTables(int numberOfPeople, String reservationName) {
        if (!hasAvailableTables(numberOfPeople)) {
            return false;
        }

        double neededTables = Math.ceil(numberOfPeople / 6.0);


        for (int i = currentTableIndex; i < currentTableIndex + neededTables; i++) {
            if (!tables[i].isOccupied()) {
                int seatToReserve = Math.min(Table.CAPACITY, numberOfPeople);
                tables[i].reserve(seatToReserve, reservationName);
                numberOfPeople -= seatToReserve;
                availableTables--;
                currentTableIndex++;
            }
            if (numberOfPeople == 0){break;}
        }
        return true;
    }

    public String availableTableInfo(int numberOfPeople) {
        StringBuilder info = new StringBuilder("Available tables: " + availableTables + "\n");
        double neededTables = Math.ceil(numberOfPeople / 6.0);
        for (int i = currentTableIndex; i < currentTableIndex + neededTables; i++) {
            info.append("Table: ").append(i + 1).append("\n");
        }
        return info.toString();
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder("");
        for (int i = 0; i < totalTables; i++) {
            details.append("Table ").append(i + 1).append(": ").append(tables[i].toString()).append("\n");
        }
        for (int i = 0; i < additionalTables; i++) {
            details.append("Extra table ").append(i + 1).append(": ").append(tables[totalTables + i].toString()).append("\n");
        }
        return details.toString();
    }
}