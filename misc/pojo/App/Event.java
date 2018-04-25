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
public class Event {
    private transient String eID;
    private String name;
    private String description;
    private int maxParticipators;
    private int minAge;
    private transient int totalLikes;
    private transient int totalParticipators;
    private LocalDate startDate;
    private LocalDate endDate;
    private transient LocalDate created;
    private transient LocalDate lastEdited;
    private EventType type;
    private transient EventState state;
    private EventCategory category;
    private transient ArrayList<MinimalUser> participators;
    private MinimalUser creator;

    public Event(String name, String description, int maxParticipators, int minAge, LocalDate startDate, LocalDate endDate, EventType type, EventCategory category, MinimalUser creator) {
        this.name = name;
        this.description = description;
        this.maxParticipators = maxParticipators;
        this.minAge = minAge;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.category = category;
        this.creator = creator;
    }

    public String geteID() {
        return eID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxParticipators() {
        return maxParticipators;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getTotalParticipators() {
        return totalParticipators;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getLastEdited() {
        return lastEdited;
    }

    public EventType getType() {
        return type;
    }

    public EventState getState() {
        return state;
    }

    public EventCategory getCategory() {
        return category;
    }

    public ArrayList<MinimalUser> getParticipators() {
        return participators;
    }

    public MinimalUser getCreator() {
        return creator;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxParticipators(int maxParticipators) {
        this.maxParticipators = maxParticipators;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }
    
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}