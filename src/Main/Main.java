package Main;

import ScooterApp.ScooterApp;
import Scooter.Scooter;
import User.User;

public class Main {
    public static void main(String[] args) {
        ScooterApp app = new ScooterApp();

        // Register a user for testing
        User alice = app.registerUser("alice", "password123", 25);

        // Create a scooter
        Scooter scooter1 = app.createScooter("Manchester"); // Create a scooter in Manchester

        // Rent the scooter to Alice
        Scooter scooterToRent = null;
        for (Scooter scooter : app.getStations().get("Manchester")) {
            if (scooter.getSerial() == 0) { // or any condition to uniquely identify the scooter
                scooterToRent = scooter;
                break;
            }
        }

        if (scooterToRent != null) {
            app.rentScooter(scooterToRent, alice); // Rent the scooter
        } else {
            System.out.println("Scooter not found.");
        }

        // Print the status after renting the scooter
        app.print();
    }
}