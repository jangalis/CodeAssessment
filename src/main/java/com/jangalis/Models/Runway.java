package com.jangalis.Models;

public class Runway {

    private String id;
    private String surface;
    private String airportRef;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getAirportRef() {
        return airportRef;
    }

    public void setAirportRef(String airportRef) {
        this.airportRef = airportRef;
    }
}
