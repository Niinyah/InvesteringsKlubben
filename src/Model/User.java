package Model;

import java.time.LocalDate;

public class User {

    private final String userID;
    private final String fullName;
    private final String email;
    private final LocalDate birthDate;
    private final double initialCashDKK;
    private final LocalDate createdAt;
    private final LocalDate lastUpdated;



    public User(String userID, String fullName, String email, LocalDate birthDate, double initialCashDKK, LocalDate createdAt, LocalDate lastUpdated) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.initialCashDKK = initialCashDKK;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
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
