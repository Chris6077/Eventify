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
public class Event implements Serializable {
    private String eID;
    private String name;
    private String creatorID;
    private EventState state;
    private String description;
    private int maxParticipants;
    private int minAge;
    private int totalParticipators;
    private EventType type;
    private EventCategory category;
    private Date startDate;
    private Date endDate;
    private final Date created;
    private Date lastEdited;
    private Location location;

    public Event(String name, String creatorID, EventState state, String description, int maxParticipants,int totalParticipators, int minAge, EventType type, EventCategory category, Date startDate, Date endDate)    {
        this.name = name;
        this.creatorID = creatorID;
        this.state = state;
        this.description = description;
        this.maxParticipants = maxParticipants;
        this.minAge = minAge;
        this.totalParticipators = totalParticipators;
        this.type = type;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.created = null;
        this.lastEdited = null;
    }
    
    public String getEID() {
        return eID;
    }
    
    public void setEID(String eID) {
        this.eID = eID;
    }

    public String getName() {
        return name;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public EventState getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getMinAge() {
        return minAge;
    }

    public EventType getType() {
        return type;
    }

    public EventCategory getCategory() {
        return category;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTotalParticipators() {
        return totalParticipators;
    }

    public void setTotalParticipators(int totalParticipators) {
        this.totalParticipators = totalParticipators;
    }

    @Override
    public String toString() {
        return "{ 'eID': '" + eID + "', 'name': '" + name + "', 'creatorID': '" + creatorID + "', 'state': '" + state + "', 'description': '" + description + "', 'maxParticipants': '" + maxParticipants + "', 'minAge': '" + minAge + "', 'type': '" + type + "', 'category': '" + category + "', 'startDate': '" + startDate + "', 'endDate': '" + endDate + "', 'created': '" + created + "', 'lastEdited': '" + lastEdited + "', 'location': '" + location + "' }";
    }
}
