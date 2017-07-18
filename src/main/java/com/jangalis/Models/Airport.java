package com.jangalis.Models;

public class Airport {

    private String id;
    private String airportIdent;
    private String airportRef;
    private String country;
    private String countryCode;
    private String runwaySurface;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirportIdent() {
        return airportIdent;
    }

    public void setAirportIdent(String airportIdent) {
        this.airportIdent = airportIdent;
    }

    public String getAirportRef() {
        return airportRef;
    }

    public void setAirportRef(String airportRef) {
        this.airportRef = airportRef;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRunwaySurface() {
        return runwaySurface;
    }

    public void setRunwaySurface(String runwaySurface) {
        this.runwaySurface = runwaySurface;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "<td>" + country + "</td>" + "<td>" + id + "</td>" + "<td>" + name + "</td>" + "<td>" + airportRef +
                "</td>" + "<td>" + runwaySurface + "</td>" + "<td>" + airportIdent + "</td>";
    }
}
