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

        Map<Integer, Set<Integer>> stationRoutesMap = routeWithStationsMapAggregator.routesAtStation.getStationToRoutesMap();

        routesAtStation.setRouteStations(mergeStationRoutesMaps(stationRoutesMap));
        return this;
    }

    private Map<Integer, Set<Integer>> mergeStationRoutesMaps(Map<Integer, Set<Integer>> stationRoutesMap) {
        return Stream.of(stationRoutesMap, routesAtStation.getStationToRoutesMap())
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                    HashSet<Integer> both = new HashSet<>(a);
                    both.addAll(b);
                    return both;
                }));
    }
}
