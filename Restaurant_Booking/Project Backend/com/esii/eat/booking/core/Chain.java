package com.esii.eat.booking.core;

public class Chain {
    private String chainName;
    private int numberOfRestaurants;
    private Restaurant[] restaurants;

    // Constructor
    public Chain(String chainName, int numberOfRestaurants) {
        this.chainName = chainName;
        this.numberOfRestaurants = numberOfRestaurants;
        this.restaurants = new Restaurant[numberOfRestaurants];
    }

    // Getters
    public String getChainName() {
        return chainName;
    }

    public int getNumberOfRestaurants() {
        return numberOfRestaurants;
    }

    public boolean addRestaurant(Restaurant restaurant) {
        for (int i = 0; i < restaurants.length; i++) {
            if (restaurants[i] == null) {
                restaurants[i] = restaurant;
                return true;
            }
        }
        return false;
    }

    public int getRestaurantPosition(String name) {
        for (int i = 0; i < restaurants.length; i++) {
            if (restaurants[i] != null && restaurants[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Restaurant getRestaurant(String name) {
        int pos = getRestaurantPosition(name);
        if (pos != -1) {
            return restaurants[pos];
        }
        return null;
    }

    public boolean reserveRestaurant(int numberOfPeople, String restaurantName, String reservationName) {
        Restaurant restaurant = getRestaurant(restaurantName);
        if (restaurant != null) {
            return restaurant.reserveTables(numberOfPeople, reservationName);
        }
        return false;
    }

    public Restaurant searchRestaurant(String restaurantName, int numberOfPeople) {
        int startPos = getRestaurantPosition(restaurantName);
        if (startPos == -1) return null;

        int i = (startPos + 1) % numberOfRestaurants;
        while (i != startPos) {
            if (restaurants[i] != null && restaurants[i].hasAvailableTables(numberOfPeople)) {
                return restaurants[i];
            }
            i = (i + 1) % numberOfRestaurants;
        }

        return null;
    }
}