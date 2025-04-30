package Model;

import java.time.LocalDate;

public class User {

    private String userID;
    private String fullName;
    private String email;
    private LocalDate birthDate;
    private double initialCashDKK;
    private LocalDate createdAt;
    private LocalDate lastUpdated;
    private String firstName;
    private String lastName;

    public User(String userID, String fullName, String email, LocalDate birthDate, double initialCashDKK, LocalDate createdAt, LocalDate lastUpdated) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.initialCashDKK = initialCashDKK;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        splitName();
    }

    public String getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getInitialCashDKK() {
        return initialCashDKK;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void splitName() {
        String[] split = fullName.split(" ");
        this.firstName = split[0];
        this.lastName = split[1];
    }

    public String toString(){
        return "User: " + userID
                + " Full name: " + fullName
                + " Email: "  + email
                + " Birth date:" + birthDate
                + " Initial cash: " + initialCashDKK
                + " Created at " + createdAt
                + " Last updated " + lastUpdated;
    }
}
