/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.schueler.eventures.classes.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Chris
 */
public class User implements Serializable {
    private String uID;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private UserType type;
    private UserState state;
    private final Date created;
    private Date lastEdited;
    private int numberOfCreated;
    private int numberOfParticipated;

    public User(String firstName, String lastName, Date birthDate, String username, String email, String password, String profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        type = UserType.User;
        state = UserState.Activated;
        created = null;
        lastEdited = null;
        numberOfCreated = 0;
        numberOfParticipated = 0;
    }
    
    public String getUID(){
        return uID;
    }
    
    public void setUID(String uID){
        this.uID = uID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getUsername() {
        return username;
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

    public UserType getType() {
        return type;
    }

    public UserState getState() {
        return state;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public int getNumberOfCreated() {
        return numberOfCreated;
    }

    public int getNumberOfParticipated() {
        return numberOfParticipated;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setType(UserType type) {
        this.type = type;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public void setNumberOfCreated(int numberOfCreated) {
        this.numberOfCreated = numberOfCreated;
    }

    public void setNumberOfParticipated(int numberOfParticipated) {
        this.numberOfParticipated = numberOfParticipated;
    }

    @Override
    public String toString() {
        return "{ 'uID': '" + uID + "', 'firstName': '" + firstName + "', 'lastName': '" + lastName + "', 'birthDate': '" + birthDate + "', 'username': '" + username + "', 'email': '" + email + "', 'password': '" + password + "', 'profilePicture': '" + profilePicture + "', 'type': '" + type + "', 'state': '" + state + "', 'created': '" + created + "', 'lastEdited': '" + lastEdited + "', 'numberOfCreatedEvents': '" + numberOfCreated + "', 'numberOfParticipated': '" + numberOfParticipated + "' }";
    }
}
