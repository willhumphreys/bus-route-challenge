package com.goeuro.challenges.data;

import java.util.*;

class StopsAndRoutes {
    private Map<Integer, Set<Integer>> stopRoutesMap = new HashMap<>();

    void add(RouteWithStops routeWithStops) {
        Integer route = routeWithStops.getRoute();
        Set<Integer> stops = routeWithStops.getStops();
        stops.forEach(stop -> {
            Set<Integer> routes = stopRoutesMap.computeIfAbsent(stop, k -> new HashSet<>());
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
