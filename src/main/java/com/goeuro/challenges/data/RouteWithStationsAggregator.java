package com.goeuro.challenges.data;

import java.util.function.Supplier;

public class RouteWithStationsAggregator implements Supplier<RouteWithStationsAggregator> {

    private final RouteWithStations.Builder routeWithStationsBuilder;

    RouteWithStationsAggregator() {
        routeWithStationsBuilder = new RouteWithStations.Builder();
    }

    RouteWithStationsAggregator sum(RouteWithStationsAggregator routeWithStationsAggregator) {
        routeWithStationsBuilder.getStations()
                .addAll(routeWithStationsAggregator.routeWithStationsBuilder.getStations().build());
        return this;
    }

    RouteWithStations finish(int route) {
        return this.routeWithStationsBuilder.setRoute(route).build();
    }

    @Override
    public RouteWithStationsAggregator get() {
        return new RouteWithStationsAggregator();
    }

    RouteWithStationsAggregator addStation(Integer station) {
        this.routeWithStationsBuilder.addStation(station);
        return this;
    }
}
