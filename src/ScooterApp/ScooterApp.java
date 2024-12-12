package ScooterApp;  // Package declaration

import Scooter.Scooter;  // Importing the Scooter class
import User.User;  // Importing the User class
import java.util.ArrayList;  // Importing ArrayList for storing scooters at stations
import java.util.HashMap;  // Importing HashMap for managing stations and users
import java.util.List;  // Importing List for storing scooters at each station
import java.util.Map;  // Importing Map for managing stations and registered users

public class ScooterApp {
    private Map<String, List<Scooter>> stations;  // A map to hold the stations and their scooters
    private Map<String, User> registeredUsers;   // A map to hold registered users

    // Constructor to initialize the scooter app
    public ScooterApp() {
        stations = new HashMap<>();  // Initialize the stations map
        stations.put("Manchester", new ArrayList<>());  // Add default stations
        stations.put("Birmingham", new ArrayList<>());
        stations.put("London", new ArrayList<>());
        registeredUsers = new HashMap<>();  // Initialize the registered users map
    }

    // Method to register a new user
    public User registerUser(String username, String password, int age) {
        // Check if the username is not already registered and the user is 18 or older
        if (!registeredUsers.containsKey(username) && age >= 18) {
            registeredUsers.put(username, new User(username, password, age));  // Add user to registered users
            System.out.println("User " + username + " has been registered.");  // Log successful registration
            return registeredUsers.get(username);  // Return the registered user
        } else if (age < 18) {  // If age is less than 18
            System.out.println("Sorry, you're too young.");  // Inform user they are too young
        } else {  // If username already exists
            System.out.println(username + " has already registered.");  // Inform user about existing registration
        }
        return null;  // Return null if registration fails
    }

    // Method to log in a user
    public void loginUser(String username, String password) {
        User user = registeredUsers.get(username);  // Retrieve the user by username
        if (user != null && user.getPassword().equals(password)) {  // Check if user exists and password matches
            user.login(password);  // Call the login method on the User object
            System.out.println("User " + username + " has been logged in.");
        } else {
            throw new RuntimeException("Username or password is incorrect.");  // Throw error if login fails
        }
    }

    // Method to log out a user
    public void logoutUser(String username) {
        User user = registeredUsers.get(username);  // Retrieve the user by username
        if (user != null) {
            user.logout();  // Call the logout method on the User object
            System.out.println("User " + username + " has been logged out.");
        } else {
            throw new RuntimeException("No such user is logged in.");  // Throw error if no such user exists
        }
    }

    // Method to create a new scooter
    public Scooter createScooter(String station) {
        Scooter scooter = new Scooter(station);  // Create a new Scooter with the given station
        System.out.println("New Scooter has been created.");  // Log the creation
        if (!stations.containsKey(station)) {
            throw new RuntimeException("No such station is found.");  // Throw error if station does not exist
        }
        stations.get(station).add(scooter);  // Add the scooter to the corresponding station's list
        return scooter;  // Return the created scooter
    }

    // Method to dock a scooter at a station
    public void dockScooter(Scooter scooter, String station) {
        if (!stations.containsKey(station)) {  // Check if the station exists
            throw new RuntimeException("No such station is found.");  // Throw error if station does not exist
        }
        if (!stations.get(station).contains(scooter)) {  // Check if the scooter is already docked
            stations.get(station).add(scooter);  // Add the scooter to the station's list
            System.out.println("Scooter is docked.");  // Log success message
        } else {
            throw new RuntimeException("Scooter has already been docked.");  // Scooter already exists at the station
        }
    }

    // Method to rent a scooter to a user
    public void rentScooter(Scooter scooter, User user) {
        if (scooter.getUser() != null) {  // Check if the scooter is already rented
            throw new RuntimeException("Scooter has already been rented.");  // Throw error if already rented
        }
        if (stations.containsKey(scooter.getStation())) {  // Check if the station exists
            stations.get(scooter.getStation()).remove(scooter);  // Remove scooter from station's list
            scooter.rent(user);  // Rent the scooter to the user
            System.out.println("Scooter is rented");  // Log success message
        } else {
            throw new RuntimeException("Scooter not found at any station.");  // Scooter not found at any station
        }
    }

    // Method to print registered users and station details
    public void print() {
        System.out.println("Registered Users:");  // Log registered users
        registeredUsers.forEach((username, user) -> System.out.println(user));  // Print each user's details

        System.out.println("\nStations and Scooter Counts:");  // Log stations and scooter counts
        stations.forEach((station, scooters) ->
                System.out.println("Station: " + station + ", Number of scooters: " + scooters.size()));  // Print each station's scooter count
    }

    // Getter for stations map
    public Map<String, List<Scooter>> getStations() {
        return stations;  // Return the stations map
    }

    // Setter for stations map
    public void setStations(Map<String, List<Scooter>> stations) {
        this.stations = stations;  // Set the stations map
    }

    // Getter for registered users map
    public Map<String, User> getRegisteredUsers() {
        return registeredUsers;  // Return the registered users map
    }

    // Setter for registered users map
    public void setRegisteredUsers(Map<String, User> registeredUsers) {
        this.registeredUsers = registeredUsers;  // Set the registered users map
    }
}

