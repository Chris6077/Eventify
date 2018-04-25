/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class User {
    private transient int uID;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String profilePicture;
    private transient UserState state;
    private transient UserType type;
    private transient int numberOfCreated;
    private transient int numberOfParticipated;
    private transient double rating;
    private transient int totalFollowers;
    private transient LocalDate created;
    private transient LocalDate lastEdited;
    private transient ArrayList<SlimUser> follows;
    private transient ArrayList<SlimEvent> likes;
    private transient ArrayList<SlimEvent> participatesIn;

    public User(String firstName, String lastName, LocalDate birthdate, String email, String password, String profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public int getuID() {
        return uID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserState getState() {
        return state;
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

    public LocalDate getLastEdited() {
        return lastEdited;
    }

    public ArrayList<SlimUser> getFollows() {
        return follows;
    }

    public ArrayList<SlimEvent> getLikes() {
        return likes;
    }

    public ArrayList<SlimEvent> getParticipatesIn() {
        return participatesIn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }   
}