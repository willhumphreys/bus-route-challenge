package com.goeuro.challenges.data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteWithStationsMapAggregator implements Supplier<RouteWithStationsMapAggregator> {

    private final RoutesAtStation routesAtStation;

    public RouteWithStationsMapAggregator() {
        this.routesAtStation = new RoutesAtStation();
    }

    public void add(RouteWithStations routeWithStations) {
        this.routesAtStation.add(routeWithStations);
    }

    public RoutesAtStation finish() {
        return this.routesAtStation;
    }

    @Override
    public RouteWithStationsMapAggregator get() {
        return new RouteWithStationsMapAggregator();
    }

    public RouteWithStationsMapAggregator sum(RouteWithStationsMapAggregator routeWithStationsMapAggregator) {

        Map<Integer, Set<Integer>> stopRoutesMap = routeWithStationsMapAggregator.routesAtStation.getMap();

        Map<Integer, Set<Integer>> merged = Stream.of(stopRoutesMap, routesAtStation.getMap())
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                    HashSet<Integer> both = new HashSet<>(a);
                    both.addAll(b);
                    return both;
                }));

        routesAtStation.setRouteStations(merged);
        return this;
    }
}
