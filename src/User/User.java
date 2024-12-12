package User;  // Package declaration

import Scooter.Scooter;  // Importing Scooter class for potential scooter-related operations

public class User {
    private String username;  // The username of the user
    private String password;  // The password of the user
    private int age;  // The age of the user
    private boolean loggedIn;  // A flag to check if the user is logged in or not

    // Constructor to initialize user properties
    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
        loggedIn = false;  // Default to not logged in
    }

    // Method to log in the user
    public void login(String password) {
        if (password.equals(this.password)) {  // Check if the provided password matches
            this.loggedIn = true;  // Set loggedIn to true if password matches
            System.out.println("You're logged in!");  // Confirmation message
        } else {
            throw new IllegalArgumentException("Incorrect password");  // Throw exception if password is incorrect
        }
    }

    // Method to log out the user
    public void logout() {
        this.loggedIn = false;  // Set loggedIn to false to log out
    }

    // Getter for username
    public String getUsername() {
        return username;  // Return the username
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;  // Set a new username
    }

    // Getter for password
    public String getPassword() {
        return password;  // Return the password
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;  // Set a new password
    }

    // Getter for age
    public int getAge() {
        return age;  // Return the user's age
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;  // Set a new age
    }

    // Getter for loggedIn status
    public boolean isLoggedIn() {
        return loggedIn;  // Return the loggedIn status
    }

    // Setter for loggedIn status
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;  // Set the loggedIn status
    }
}

