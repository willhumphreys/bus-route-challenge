package com.goeuro.challenges;

public class DirectJourney {
    private int departureSid;
    private int arrivalSid;
    private boolean directBusRoute;


    public DirectJourney(int departureSid, int arrivalSid, boolean directBusRoute) {
        this.departureSid = departureSid;
        this.arrivalSid = arrivalSid;
        this.directBusRoute = directBusRoute;
    }

    public int getDepartureSid() {
        return departureSid;
    }

    public int getArrivalSid() {
        return arrivalSid;
    }

    public boolean isDirectBusRoute() {
        return directBusRoute;
    }

    @Override
    public String toString() {
        return "DirectJourney{" +
                "departureSid=" + departureSid +
                ", arrivalSid=" + arrivalSid +
                ", directBusRoute=" + directBusRoute +
                '}';
    }
}
