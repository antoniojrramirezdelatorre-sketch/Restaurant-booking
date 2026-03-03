# EatBooking - Restaurant Reservation System

A desktop application developed in **Java** designed for managing reservations across restaurant chains. The system utilizes a modular architecture, strictly separating business logic (Backend) from the user interface (Frontend).



## Features
* **Chain Management**: Efficient handling of multiple restaurant locations under a single brand entity via `Chain.java`[cite: 1, 2].
* **Reservation Logic**: Advanced control of table availability and status management through `Table.java` and `Restaurant.java`[cite: 3, 4].
* **Graphical User Interface**: An interactive GUI developed using **Java Swing**, centered around `Booking.java`[cite: 5, 6].
* **Modern Architecture**: Package-based structure following standard Java conventions for high maintainability[cite: 2, 6].

## Technologies
* **Language**: Java 8+ [cite: 2]
* **GUI Framework**: Swing / AWT [cite: 5, 6]
* **Recommended IDE**: IntelliJ IDEA or NetBeans

## Project Structure
The source code is organized into the following packages:

* **`com.esii.eat.booking.core`**: Contains the core logic, data handling, and business rules for chains, restaurants, and tables[cite: 1, 2, 3, 4].
* **`com.esii.eat.booking.gui`**: Contains the forms, visual components (`.form` files), and UI event handling logic[cite: 5, 6].

---

## How to Run
1. Ensure you have **Java JDK 8** or higher installed.
2. Clone the repository to your local machine.
3. Open the project in your preferred IDE (IntelliJ or NetBeans).
4. Run the `Main.java` file located in the `core` package to start the application[cite: 2].

---
**Author**: Antonio Ramírez de la Torre.  
**Version**: 1.0.0 (Final Version)
