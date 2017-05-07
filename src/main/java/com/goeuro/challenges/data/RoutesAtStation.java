package com.goeuro.challenges.data;

import com.koloboke.collect.set.IntSet;
import com.koloboke.collect.set.hash.HashIntSets;

import java.util.*;

class RoutesAtStation {
    private static final int NUMBER_OF_STATIONS = 1_000_000;
    private static final int NUMBER_OF_BUS_ROUTES = 100_000;
    static final int MAX_STATIONS_ON_A_ROUTE = 1000;
    private static final int AVERAGE_NUMBER_OF_ROUTES_PER_STOP =
            NUMBER_OF_BUS_ROUTES * MAX_STATIONS_ON_A_ROUTE / NUMBER_OF_STATIONS;

    private Map<Integer, IntSet> map = new HashMap<>(NUMBER_OF_STATIONS);

    void add(RouteWithStations routeWithStations) {
        int route = routeWithStations.getRoute();
        Set<Integer> stations = routeWithStations.getStations();
        stations.forEach(station -> {
            IntSet routes = map.computeIfAbsent(
                    station, k -> HashIntSets.newMutableSet(AVERAGE_NUMBER_OF_ROUTES_PER_STOP));
            routes.add(route);
        });
    }

    Map<Integer, IntSet> getMap() {
        return map;
    }

    void setRouteStations(Map<Integer, IntSet> merged) {
        this.map = merged;
    }

    Optional<IntSet> get(int stationSid) {
        return Optional.ofNullable(map.get(stationSid));
    }
}
