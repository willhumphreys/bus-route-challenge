package com.goeuro.challenges.data;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

class RouteWithStations {

    private final int route;
    private final Set<Integer> stations;

    private RouteWithStations(int route, Set<Integer> stations) {
        this.route = route;
        this.stations = stations;
    }

    Integer getRoute() {
        return route;
    }

    Set<Integer> getStations() {
        return stations;
    }

    static class Builder {

        private final ImmutableSet.Builder<Integer> stations;
        private int route;

        Builder() {
            this.stations = new ImmutableSet.Builder<>();
        }

        RouteWithStations build() {
            return new RouteWithStations(route, stations.build());
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
