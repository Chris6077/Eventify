/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.time.LocalDate;

/**
 *
 * @author Chris
 */
public class SlimUser {
    private String uID;
    private transient String firstName;
    private transient String lastName;
    private transient String profilePicture;
    private transient UserType type;
    private transient int numberOfCreated;
    private transient int numberOfParticipated;
    private transient double rating;
    private transient int totalFollowers;
    private transient LocalDate created;

    public SlimUser(String uID, String firstName, String lastName, String profilePicture, UserType type, int numberOfCreated, int numberOfParticipated, double rating, int totalFollowers, LocalDate created) {
        this.uID = uID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.type = type;
        this.numberOfCreated = numberOfCreated;
        this.numberOfParticipated = numberOfParticipated;
        this.rating = rating;
        this.totalFollowers = totalFollowers;
        this.created = created;
    }

    public String getuID() {
        return uID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserType getType() {
        return type;
    }

    public int getNumberOfCreated() {
        return numberOfCreated;
    }

    public int getNumberOfParticipated() {
        return numberOfParticipated;
    }

    public double getRating() {
        return rating;
    }

    public int getTotalFollowers() {
        return totalFollowers;
    }

    public LocalDate getCreated() {
        return created;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }   
}