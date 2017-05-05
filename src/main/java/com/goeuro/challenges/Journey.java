package com.goeuro.challenges;

public class Journey {
    private int departureSid;
    private int arrivalSid;

    public Journey(int departureSid, int arrivalSid) {
        this.departureSid = departureSid;
        this.arrivalSid = arrivalSid;
    }

    public int getDepartureSid() {
        return departureSid;
    }

    public int getArrivalSid() {
        return arrivalSid;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "departureSid=" + departureSid +
                ", arrivalSid=" + arrivalSid +
                '}';
    }
}
