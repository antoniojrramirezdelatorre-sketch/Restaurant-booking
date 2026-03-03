package com.esii.eat.booking.core;

public class Main {
    public static void main(String[] args) {

        Chain myChain = new Chain("Delicious Eats", 3);


        Restaurant r1 = new Restaurant("Yaya House", 5);
        Restaurant r2 = new Restaurant("Tasty food", 4);
        Restaurant r3 = new Restaurant("Burger Computer", 6);

        System.out.println(myChain.addRestaurant(r1));
        System.out.println(myChain.addRestaurant(r2));
        System.out.println(myChain.addRestaurant(r3));

        System.out.println("com.esii.eat.booking.core.Restaurant chain: " + myChain.getChainName());
        System.out.println("Number of restaurants: " + myChain.getNumberOfRestaurants());


        System.out.println("\nTrying to reserve at 'Yaya House' for 4 people...");
        /*if (myChain.reserveRestaurant(4, "Yaya House"),) {
            System.out.println("Reservation successful.");
        } else {
            System.out.println("Reservation failed.");
        }
        System.out.println("\nTrying to reserve at 'Yaya House' for 20 people...");
        if (myChain.reserveRestaurant(20, "Yaya House")) {
            System.out.println("Reservation successful.");
        } else {
            System.out.println("Reservation failed.");
        }
        System.out.println("\nTrying to reserve at 'Yaya House' for 4 people...");
        if (myChain.reserveRestaurant(4, "Yaya House")) {
            System.out.println("Reservation successful.");
        } else {
            System.out.println("Reservation failed.");
        }

        System.out.println("\nTrying to reserve at 'Tasty food' for 7 people...");
        if (myChain.reserveRestaurant(7, "Tasty food")) {
            System.out.println("Reservation successful.");
        } else {
            System.out.println("Reservation failed.");
        }


        System.out.println("\nTrying to reserve at 'Burger Computer' for 36 people...");
        if (myChain.reserveRestaurant(36, "Burger Computer")) {
            System.out.println("Reservation successful.");
        } else {
            System.out.println("Reservation failed.");
        }*/


        System.out.println("\nCurrent restaurant status:");
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);

}}

