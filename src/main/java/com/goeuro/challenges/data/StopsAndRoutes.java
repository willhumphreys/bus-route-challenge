package com.goeuro.challenges.data;

import java.util.*;

class StopsAndRoutes {
    private static final int NUMBER_OF_STATIONS = 1_000_000;
    private static final int NUMBER_OF_BUS_ROUTES = 100_000;
    private static final int MAX_STOPS_ON_A_ROUTE = 1000;
    private int AVERAGE_NUMBER_OF_ROUTES_PER_STOP =
            NUMBER_OF_BUS_ROUTES * MAX_STOPS_ON_A_ROUTE / NUMBER_OF_STATIONS;

    private Map<Integer, Set<Integer>> stopRoutesMap = new HashMap<>(NUMBER_OF_STATIONS);

    void add(RouteWithStops routeWithStops) {
        Integer route = routeWithStops.getRoute();
        Set<Integer> stops = routeWithStops.getStops();
        stops.forEach(stop -> {
            Set<Integer> routes = stopRoutesMap.computeIfAbsent(
                    stop, k -> new HashSet<>(AVERAGE_NUMBER_OF_ROUTES_PER_STOP));
            routes.add(route);
        });
    }

    Map<Integer, Set<Integer>> getStopRoutesMap() {
        return stopRoutesMap;
    }
    StopsAndRoutes build() {
        return this;
    }

    void setRouteStops(Map<Integer, Set<Integer>> merged) {
        this.stopRoutesMap = merged;
    }

    Optional<Set<Integer>> getRoutesAtStop(int departureSid) {
        return Optional.ofNullable(stopRoutesMap.get(departureSid));
    }
}
