package com.goeuro.challenges.data;

import java.util.function.Supplier;

public class RouteWithStationsAggregator implements Supplier<RouteWithStationsAggregator> {

    private boolean isRouteSet;
    private final RouteWithStations.Builder routeWithStationsBuilder;

    RouteWithStationsAggregator() {
        routeWithStationsBuilder = new RouteWithStations.Builder();
    }

    RouteWithStationsAggregator sum(RouteWithStationsAggregator routeWithStationsAggregator) {
        routeWithStationsBuilder.getStations()
                .addAll(routeWithStationsAggregator.routeWithStationsBuilder.getStations().build());
        return this;
    }

    RouteWithStations finish() {
        return this.routeWithStationsBuilder.build();
    }

    @Override
    public RouteWithStationsAggregator get() {
        return new RouteWithStationsAggregator();
    }

    void addData(Integer routeOrStop) {
        if(isRouteSet) {
            this.routeWithStationsBuilder.setRoute(routeOrStop);
            isRouteSet = false;
        } else {
            this.routeWithStationsBuilder.addStation(routeOrStop);
        }
    }
}
