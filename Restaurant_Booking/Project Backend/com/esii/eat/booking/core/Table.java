package com.esii.eat.booking.core;

public class Table {
    static final int CAPACITY = 6;
    private boolean isOccupied;
    private int occupiedSeats;
    private String reservationName;

    public Table(){
        this.isOccupied = false;
        this.occupiedSeats = 0;
    }

    public boolean isOccupied(){
        if(this.isOccupied) return true;
        return false;
    }

    public void reserve(int numberOfPeople, String reservationName){
        if(numberOfPeople <= CAPACITY){
            this.isOccupied = true;
            this.occupiedSeats = numberOfPeople;
            this.reservationName = reservationName;
        }
    }

    @Override
    public String toString(){
        if(this.isOccupied()){
            return "Reserved by " + this.reservationName + " (" + this.occupiedSeats + " people)";
        }
        return "Available";
    }
}