package com.jangalis.Models;

public class Country implements Comparable{

    private String name;
    private String code;
    private int amount = 0;

    /*
    Counters for runway types, due to amount of types I chose only few most used and rest put into otherQty
     */
    private int aspQty = 0;
    private int turfQty = 0;
    private int concQty = 0;
    private int conQty = 0;
    private int turfgQty = 0;
    private int grsQty = 0;
    private int greQty = 0;
    private int asphQty = 0;
    private int unkQty = 0;
    private int otherQty = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAspQty() {
        return aspQty;
    }

    public void setAspQty(int aspQty) {
        this.aspQty = aspQty;
    }

    public int getTurfQty() {
        return turfQty;
    }

    public void setTurfQty(int turfQty) {
        this.turfQty = turfQty;
    }

    public int getConcQty() {
        return concQty;
    }

    public void setConcQty(int concQty) {
        this.concQty = concQty;
    }

    public int getConQty() {
        return conQty;
    }

    public void setConQty(int conQty) {
        this.conQty = conQty;
    }

    public int getTurfgQty() {
        return turfgQty;
    }

    public void setTurfgQty(int turfgQty) {
        this.turfgQty = turfgQty;
    }

    public int getGrsQty() {
        return grsQty;
    }

    public void setGrsQty(int grsQty) {
        this.grsQty = grsQty;
    }

    public int getGreQty() {
        return greQty;
    }

    public void setGreQty(int greQty) {
        this.greQty = greQty;
    }

    public int getAsphQty() {
        return asphQty;
    }

    public void setAsphQty(int asphQty) {
        this.asphQty = asphQty;
    }

    public int getUnkQty() {
        return unkQty;
    }

    public void setUnkQty(int unkQty) {
        this.unkQty = unkQty;
    }

    public int getOtherQty() {
        return otherQty;
    }

    public void setOtherQty(int otherQty) {
        this.otherQty = otherQty;
    }

    @Override
    public int compareTo(Object o) {
        int compareQuantity = ((Country) o).getAmount();
        return this.getAmount() - compareQuantity;
    }

    @Override
    public String toString() {
        return "<td>" + name + "</td>" + "<td>" + amount + "</td>";
    }

    public String surfacesToString() {
        return "<td>" + name + "</td>" +
                "<td>" + aspQty + "</td>" +
                "<td>" + turfQty  + "</td>" +
                "<td>" + concQty  + "</td>" +
                "<td>" + conQty + "</td>" +
                "<td>" + turfgQty + "</td>" +
                "<td>" + grsQty + "</td>" +
                "<td>" + greQty + "</td>" +
                "<td>" + asphQty  + "</td>" +
                "<td>" + unkQty + "</td>" +
                "<td>" + otherQty + "</td>";
    }
}
