package com.goeuro.challenges.data;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

class RouteWithStops {

    private final int route;
    private final Set<Integer> stops;

    private RouteWithStops(int route, Set<Integer> stops) {
        this.route = route;
        this.stops = stops;
    }

    Integer getRoute() {
        return route;
    }

    Set<Integer> getStops() {
        return stops;
    }

    static class Builder {

        private final ImmutableSet.Builder<Integer> stations;
        private int route;

        Builder() {
            this.stations = new ImmutableSet.Builder<>();
        }

        RouteWithStops build() {
            return new RouteWithStops(route, stations.build());
        }

        ImmutableSet.Builder<Integer> getStations() {
            return stations;
        }

        public Builder setRoute(int route) {
            this.route = route;
            return this;
        }

        public Builder addStation(int station) {
            this.stations.add(station);
            return this;
        }
    }
}
