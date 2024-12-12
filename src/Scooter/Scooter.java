package Scooter;

import User.User;

import java.util.Timer;
import java.util.TimerTask;

public class Scooter {
    // Fields to store scooter information
    private String station;       // The station where the scooter is currently located
    private User user;             // The user who rented the scooter
    private int serial;            // Unique serial number for each scooter
    private int charge;            // Current battery charge level (0-100)
    private boolean isBroken;      // Indicates if the scooter is currently broken
    private static int nextSerial; // Counter to generate unique serial numbers for each scooter

    // Constructor to initialize a scooter with a station
    public Scooter(String station) {
        this.station = station;       // Set the station
        this.user = null;             // Initially, the scooter has no user
        this.serial = nextSerial++;   // Assign a unique serial number
        this.charge = 100;            // Set the charge level to 100% initially
        this.isBroken = false;        // Set the scooter as not broken
    }

    // Method to rent a scooter to a user
    public void rent(User user) {
        if (charge <= 20) {   // Check if the scooter has sufficient charge
            throw new IllegalArgumentException("Scooter needs charge"); // Throw exception if not charged enough
        } else if (!isBroken) {   // Check if the scooter is not broken
            station = null;    // The scooter is no longer at the station
            this.user = user;  // Assign the user to the scooter
        } else {
            throw new IllegalArgumentException("Scooter needs repair"); // Throw exception if the scooter is broken
        }
    }

    // Method to dock a scooter at a station
    public void dock(String station) {
        this.station = station;  // Set the new station for the scooter
        this.user = null;        // The scooter is no longer rented by any user
    }

    // Method to recharge the scooter battery
    public void recharge() {
        // Create a new Timer instance to manage the charging process
        Timer timer = new Timer();

        // Create a TimerTask to perform the charging logic
        TimerTask task = new TimerTask() {
            int currentCharge = charge; // Track current charging progress

            // The run method is called repeatedly by the timer
            @Override
            public void run() {
                if (currentCharge < 100) {  // Check if the charge level needs more boost
                    currentCharge += 10;    // Increase charge by 10%
                    charge = currentCharge; // Update the scooter's charge
                    System.out.println("Charge now: " + charge + "%"); // Log the current charge
                } else {
                    System.out.println("Fully charged"); // Log when charging is complete
                    timer.cancel();  // Stop the timer
                }
            }
        };

        // Schedule the task to run every 1 second
        timer.scheduleAtFixedRate(task, 0, 1000); // Start immediately and repeat every 1 second
    }

    // Method to request repair for a broken scooter
    public void requestRepair() {
        if (!isBroken) {  // Check if the scooter is not broken
            System.out.println("Scooter doesn't need repair"); // Log message if no repair needed
            return;  // Exit early if no repair needed
        }

        // Create a Timer to schedule the repair completion
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isBroken = false;  // Mark the scooter as not broken
                System.out.println("Repair complete"); // Log when repair is done
            }
        };

        // Schedule the repair task to run after 5 seconds
        timer.schedule(task, 0, 5000); // Schedule to run after 5 seconds
    }

    // Getter and Setter methods

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public static int getNextSerial() {
        return nextSerial;
    }

    public static void setNextSerial(int nextSerial) {
        Scooter.nextSerial = nextSerial;
    }
}
