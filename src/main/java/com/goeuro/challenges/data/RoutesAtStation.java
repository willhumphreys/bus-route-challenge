package com.goeuro.challenges.data;

import java.util.*;

class RoutesAtStation {
    private static final int NUMBER_OF_STATIONS = 1_000_000;
    private static final int NUMBER_OF_BUS_ROUTES = 100_000;
    private static final int MAX_STOPS_ON_A_ROUTE = 1000;
    private int AVERAGE_NUMBER_OF_ROUTES_PER_STOP =
            NUMBER_OF_BUS_ROUTES * MAX_STOPS_ON_A_ROUTE / NUMBER_OF_STATIONS;

    private Map<Integer, Set<Integer>> map = new HashMap<>(NUMBER_OF_STATIONS);

    void add(RouteWithStops routeWithStops) {
        Integer route = routeWithStops.getRoute();
        Set<Integer> stops = routeWithStops.getStops();
        stops.forEach(stop -> {
            Set<Integer> routes = map.computeIfAbsent(
                    stop, k -> new HashSet<>(AVERAGE_NUMBER_OF_ROUTES_PER_STOP));
            routes.add(route);
        });
    }

    Map<Integer, Set<Integer>> getMap() {
        return map;
    }

    void setRouteStops(Map<Integer, Set<Integer>> merged) {
        this.map = merged;
    }

    Optional<Set<Integer>> get(int stationSid) {
        return Optional.ofNullable(map.get(stationSid));
    }
}
