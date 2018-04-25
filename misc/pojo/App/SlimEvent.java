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
public class SlimEvent {
    private String eID;
    private transient String name;
    private transient int totalLikes;
    private transient int maxParticipators;
    private transient int totalParticipators;

    public SlimEvent(String eID, String name, int totalLikes, int maxParticipators, int totalParticipators) {
        this.eID = eID;
        this.name = name;
        this.totalLikes = totalLikes;
        this.maxParticipators = maxParticipators;
        this.totalParticipators = totalParticipators;
    }

    public String geteID() {
        return eID;
    }

    public String getName() {
        return name;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getMaxParticipators() {
        return maxParticipators;
    }

    public int getTotalParticipators() {
        return totalParticipators;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }   
}