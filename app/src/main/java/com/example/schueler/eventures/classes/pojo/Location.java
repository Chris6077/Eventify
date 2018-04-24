/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.schueler.eventures.classes.pojo;

import java.io.Serializable;

/**
 *
 * @author Chris
 */
public class Location implements Serializable {
    private final double lat;
    private final double lon;
    
    public Location(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "{ 'lat': '" + lat + "', 'lon': '" + lon + "' }";
    }
}