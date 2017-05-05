package com.goeuro.challenges.data;

import java.util.function.Supplier;

public class RouteWithStopsAggregator implements Supplier<RouteWithStopsAggregator> {

    private boolean isRouteSet;
    private final RouteWithStops.Builder routeWithStopsBuilder;

    RouteWithStopsAggregator() {
        routeWithStopsBuilder = new RouteWithStops.Builder();
    }

    RouteWithStopsAggregator sum(RouteWithStopsAggregator routeWithStopsAggregator) {
        routeWithStopsBuilder.getStations()
                .addAll(routeWithStopsAggregator.routeWithStopsBuilder.getStations().build());
        return this;
    }

    RouteWithStops finish() {
        return this.routeWithStopsBuilder.build();
    }

    @Override
    public RouteWithStopsAggregator get() {
        return new RouteWithStopsAggregator();
    }

    void addData(Integer routeOrStop) {
        if(isRouteSet) {
            this.routeWithStopsBuilder.setRoute(routeOrStop);
            isRouteSet = false;
        } else {
            this.routeWithStopsBuilder.addStation(routeOrStop);
        }
    }
}
