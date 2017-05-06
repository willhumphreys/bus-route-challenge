package com.goeuro.challenges.data;

import java.util.*;

class RoutesAtStation {
    private static final int NUMBER_OF_STATIONS = 1_000_000;
    private static final int NUMBER_OF_BUS_ROUTES = 100_000;
    private static final int MAX_STATIONS_ON_A_ROUTE = 1000;
    private final int AVERAGE_NUMBER_OF_ROUTES_PER_STOP =
            NUMBER_OF_BUS_ROUTES * MAX_STATIONS_ON_A_ROUTE / NUMBER_OF_STATIONS;

    private Map<Integer, Set<Integer>> map = new HashMap<>(NUMBER_OF_STATIONS);

    void add(RouteWithStations routeWithStations) {
        Integer route = routeWithStations.getRoute();
        Set<Integer> stations = routeWithStations.getStations();
        stations.forEach(station -> {
            Set<Integer> routes = map.computeIfAbsent(
                    station, k -> new HashSet<>(AVERAGE_NUMBER_OF_ROUTES_PER_STOP));
            routes.add(route);
        });
    }

    Map<Integer, Set<Integer>> getMap() {
        return map;
    }

    void setRouteStations(Map<Integer, Set<Integer>> merged) {
        this.map = merged;
    }

    Optional<Set<Integer>> get(int stationSid) {
        return Optional.ofNullable(map.get(stationSid));
    }
}
