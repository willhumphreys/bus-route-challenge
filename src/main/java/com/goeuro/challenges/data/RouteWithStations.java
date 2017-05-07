package com.goeuro.challenges.data;

import com.koloboke.collect.set.IntSet;
import com.koloboke.collect.set.hash.HashIntSet;
import com.koloboke.collect.set.hash.HashIntSets;

import static com.goeuro.challenges.data.RoutesAtStation.MAX_STATIONS_ON_A_ROUTE;

class RouteWithStations {

    private final int route;
    private final IntSet stations;

    private RouteWithStations(int route, IntSet stations) {
        this.route = route;
        this.stations = stations;
    }

    Integer getRoute() {
        return route;
    }

    IntSet getStations() {
        return stations;
    }

    static class Builder {

        private final HashIntSet stations;
        private int route;

        Builder() {
            this.stations = HashIntSets.newMutableSet(MAX_STATIONS_ON_A_ROUTE);
        }

        RouteWithStations build() {
            return new RouteWithStations(route, stations);
        }

        HashIntSet getStations() {
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
