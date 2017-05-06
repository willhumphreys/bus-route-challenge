package com.goeuro.challenges.data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteWithStationsMapAggregator implements Supplier<RouteWithStationsMapAggregator> {

    private final RoutesAtStation routesAtStation;

    RouteWithStationsMapAggregator() {
        this.routesAtStation = new RoutesAtStation();
    }

    void add(RouteWithStations routeWithStations) {
        this.routesAtStation.add(routeWithStations);
    }

    RoutesAtStation finish() {
        return this.routesAtStation;
    }

    @Override
    public RouteWithStationsMapAggregator get() {
        return new RouteWithStationsMapAggregator();
    }

    RouteWithStationsMapAggregator sum(RouteWithStationsMapAggregator routeWithStationsMapAggregator) {

        Map<Integer, Set<Integer>> stopRoutesMap = routeWithStationsMapAggregator.routesAtStation.getMap();

        routesAtStation.setRouteStations(mergeStopRoutesMaps(stopRoutesMap));
        return this;
    }

    private Map<Integer, Set<Integer>> mergeStopRoutesMaps(Map<Integer, Set<Integer>> stopRoutesMap) {
        return Stream.of(stopRoutesMap, routesAtStation.getMap())
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                    HashSet<Integer> both = new HashSet<>(a);
                    both.addAll(b);
                    return both;
                }));
    }
}
