/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author Chris
 */
public class MinimalUser {
    private String uID;
    private transient String firstName;
    private transient String lastName;
    private transient String profilePicture;

    public MinimalUser(String uID, String firstName, String lastName, String profilePicture) {
        this.uID = uID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
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
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }   
}