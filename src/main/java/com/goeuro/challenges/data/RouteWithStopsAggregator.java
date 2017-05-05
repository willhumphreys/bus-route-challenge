package com.goeuro.challenges.data;

import java.util.function.Supplier;

public class RouteWithStopsAggregator implements Supplier<RouteWithStopsAggregator> {

    private final RouteWithStops.Builder routeWithStopsBuilder;

    public RouteWithStopsAggregator() {
        routeWithStopsBuilder = new RouteWithStops.Builder();
    }

    public void add(Integer value) {
        this.routeWithStopsBuilder.setValue(value);
    }

    public RouteWithStopsAggregator sum(RouteWithStopsAggregator routeWithStopsAggregator) {
        routeWithStopsBuilder.getStops()
                .addAll(routeWithStopsAggregator.routeWithStopsBuilder.getStops().build());
        return this;
    }

    public RouteWithStops finish() {
        return this.routeWithStopsBuilder.build();
    }

    @Override
    public RouteWithStopsAggregator get() {
        return new RouteWithStopsAggregator();
    }
}
