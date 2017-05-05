package com.goeuro.challenges.data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteStopsMapAggregator implements Supplier<RouteStopsMapAggregator> {

    private RoutesAtStation routesAtStation;

    public RouteStopsMapAggregator() {
        this.routesAtStation = new RoutesAtStation();
    }

    public void add(RouteWithStops routeWithStops) {
        this.routesAtStation.add(routeWithStops);
    }

    public RoutesAtStation finish() {
        return this.routesAtStation;
    }

    @Override
    public RouteStopsMapAggregator get() {
        return new RouteStopsMapAggregator();
    }

    public RouteStopsMapAggregator sum(RouteStopsMapAggregator routeStopsMapAggregator) {

        Map<Integer, Set<Integer>> stopRoutesMap = routeStopsMapAggregator.routesAtStation.getMap();


        Map<Integer, Set<Integer>> merged = Stream.of(stopRoutesMap, routesAtStation.getMap())
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                    HashSet<Integer> both = new HashSet<>(a);
                    both.addAll(b);
                    return both;
                }));

        routesAtStation.setRouteStops(merged);
        return this;
    }
}
