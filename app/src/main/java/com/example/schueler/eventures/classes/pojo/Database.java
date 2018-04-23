package com.example.schueler.eventures.classes.pojo;

import java.util.ArrayList;

/**
 * Created by Marcel Judth on 23.04.2018.
 */

public class Database {
    public enum category{
        SPORTS,
        PARTY,
        LADIES_NIGHT
    }

    private ArrayList<Event> events;

    private Database database = null;


    public Database() {
        events = new ArrayList<>();
    }

    public void add(Event event){
        events.add(event);
    }


    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
