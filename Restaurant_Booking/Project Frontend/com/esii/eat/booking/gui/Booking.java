package com.esii.eat.booking.gui;

import com.esii.eat.booking.core.Chain;
import com.esii.eat.booking.core.Restaurant;
import javax.swing.*;

/**
 * Represents the main GUI for managing restaurant reservations within a chain.
 * This class provides a user interface for selecting restaurants, checking table availability,
 * making reservations, and searching for alternative restaurants if the preferred one is full.
 * <p>
 * The GUI is built using Swing components and interacts with the {@link Chain} and {@link Restaurant}
 * classes to manage reservations and display relevant information. All user-facing strings are loaded
 * from a {@code messages.properties} file for localization support.
 * </p>
 */
public class Booking extends JFrame {

    /** Combo box for selecting a restaurant from the chain. */
    private JComboBox<String> restaurantComboBox;

    /** Text field for entering the number of diners. */
    private JTextField dinersCountTextField;

    /** Button to check table availability for the selected restaurant. */
    private JButton checkButton;

    /** Button to make a reservation at the selected restaurant. */
    private JButton reserveButton;

    /** Button to search for alternative restaurants if the selected one is full. */
    private JButton searchAlternativeButton;

    /** Text area to display the status of tables in the selected restaurant. */
    private JTextArea restaurantTableTextArea;

    /** Text area to display suggested tables for the reservation. */
    private JTextArea suggestedTablesTextArea;

    /** Main panel containing all GUI components. */
    private JPanel mainPanel;

    /** Text field for entering the name of the person making the reservation. */
    private JTextField nameTextField;

    /** Label to display a message when no tables are available. */
    // private JLabel noTablesAvailableLabel;

    /** The restaurant chain managing all restaurants. */
    private Chain gourmetChain;

    /** An instance of the Italian Bistro restaurant. */
    private Restaurant italianBistro;

    /** An instance of the Sushi Palace restaurant. */
    private Restaurant sushiPalace;

    /** An instance of the Steak House restaurant. */
    private Restaurant steakHouse;

    /**
     * Constructs the Booking GUI and initializes the restaurant chain with predefined restaurants.
     * The GUI allows users to select a restaurant, check table availability, and make reservations.
     */
    public Booking() {
        // Initialize the main window components
        setContentPane(mainPanel);
        setTitle("Restaurant reservation system");

        // Set the size of the window to fit all components
        setSize(850, 450);

        // Center the window on the screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Initialize the restaurant chain and add restaurants
        initializeRestaurants();

        // Populate the combo box with restaurant names
        populateRestaurantComboBox();

        // Display the initial restaurant table status
        restaurantTableTextArea.setText(italianBistro.toString());

        // Add action listeners to handle user interactions
        addActionListeners();
    }

    /**
     * Initializes the restaurant chain and adds predefined restaurants to it.
     */
    private void initializeRestaurants() {
        gourmetChain = new Chain("Gourmet Dining", 3);
        italianBistro = new Restaurant("Italian Bistro", 5);
        sushiPalace = new Restaurant("Sushi Palace", 3);
        steakHouse = new Restaurant("Steak House", 4);

        gourmetChain.addRestaurant(italianBistro);
        gourmetChain.addRestaurant(sushiPalace);
        gourmetChain.addRestaurant(steakHouse);
    }

    /**
     * Populates the restaurant combo box with the names of the available restaurants.
     */
    private void populateRestaurantComboBox() {
        // Clear existing items in the combo box
        restaurantComboBox.removeAllItems();

        // Add actual restaurant names
        restaurantComboBox.addItem(italianBistro.getName());
        restaurantComboBox.addItem(sushiPalace.getName());
        restaurantComboBox.addItem(steakHouse.getName());
    }

    /**
     * Adds action listeners to the combo box, check button, reserve button, and search alternative button
     * to handle user interactions.
     */
    private void addActionListeners() {
        // Add action listener to the combo box to update the table status display
        restaurantComboBox.addActionListener(e -> updateRestaurantTableDisplay());

        // Add action listener to the check button to verify table availability
        checkButton.addActionListener(e -> checkTableAvailability());

        // Add action listener to the reserve button to make a reservation
        reserveButton.addActionListener(e -> makeReservation());

        // Add action listener to the search alternative button to find another restaurant
        searchAlternativeButton.addActionListener(e -> searchAlternativeRestaurant());

    }


    /**
     * Updates the table status display based on the selected restaurant in the combo box.
     */
    private void updateRestaurantTableDisplay() {
        // TODO: Implement table display update
        String selectedRestaurant = (String) restaurantComboBox.getSelectedItem();
        Restaurant restaurant = gourmetChain.getRestaurant(selectedRestaurant);
        restaurantTableTextArea.setText(restaurant.toString());
    }

    /**
     * Checks the availability of tables for the specified number of diners at the selected restaurant.
     * If tables are available, it enables the reserve button and displays the suggested tables.
     * If no tables are available, it shows a message and enables the search alternative button.
     */
    private void checkTableAvailability() {
        try {
            int numberOfPeople = Integer.parseInt(dinersCountTextField.getText());
            String selectedRestaurant = (String) restaurantComboBox.getSelectedItem();
            Restaurant restaurant = gourmetChain.getRestaurant(selectedRestaurant);

            if (restaurant != null && restaurant.hasAvailableTables(numberOfPeople)) {
                suggestedTablesTextArea.setText(restaurant.availableTableInfo(numberOfPeople));
                reserveButton.setEnabled(true);
                nameTextField.setEnabled(true);
                // noTablesAvailableLabel.setVisible(false);
            } else {
                // noTablesAvailableLabel.setVisible(true);
                searchAlternativeButton.setEnabled(true);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number of diners.");
        }
    }

    /**
     * Attempts to make a reservation at the selected restaurant for the specified number of diners.
     * If the reservation is successful, it displays a confirmation message and resets the form.
     */
    private void makeReservation() {
        int numberOfPeople = Integer.parseInt(dinersCountTextField.getText());
        String selectedRestaurant = (String) restaurantComboBox.getSelectedItem();
        String reservationName = nameTextField.getText();
        
        boolean reservationSuccess = gourmetChain.reserveRestaurant(numberOfPeople, selectedRestaurant, reservationName);
        if (reservationSuccess) {
            JOptionPane.showMessageDialog(null, "Reservation successful!");
            resetForm();
        } else {
            JOptionPane.showMessageDialog(null, "Reservation failed. Please try again.");
        }
    }

    /**
     * Searches for an alternative restaurant with available tables if the selected restaurant is full.
     * If an alternative is found, it prompts the user to confirm the reservation.
     */
    private void searchAlternativeRestaurant() {
        // TODO: Implement alternative restaurant search
        int numberOfPeople = Integer.parseInt(dinersCountTextField.getText());
        String selectedRestaurant = (String) restaurantComboBox.getSelectedItem();
        Restaurant alternativeRestaurant = gourmetChain.searchRestaurant(selectedRestaurant, numberOfPeople);
        if (alternativeRestaurant != null) {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "The restaurant \"" + selectedRestaurant + "\" has no available tables.\n" +
                            "Would you like to make a reservation in \"" + alternativeRestaurant.getName() + "\"?",
                    "Alternative restaurant",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                suggestedTablesTextArea.setText(alternativeRestaurant.availableTableInfo(numberOfPeople));
                restaurantComboBox.setSelectedItem(alternativeRestaurant.getName());
                reserveButton.setEnabled(true);
                nameTextField.setEnabled(true);
                // noTablesAvailableLabel.setVisible(false);
                makeReservation();
            }
        }
    }

    /**
     * Resets the form to its initial state, clearing input fields and disabling buttons.
     */
    private void resetForm() {
        // TODO: Implement form reset for the remaining and necessary components
        restaurantComboBox.setSelectedIndex(0);
        nameTextField.setText("");
        dinersCountTextField.setText("");

        updateRestaurantTableDisplay();
        suggestedTablesTextArea.setText("");
        reserveButton.setEnabled(false);
        searchAlternativeButton.setEnabled(false);

        // noTablesAvailableLabel.setVisible(false);

        nameTextField.setEnabled(false);
    }

    /**
     * Main method to launch the Booking GUI.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Booking();
    }
}