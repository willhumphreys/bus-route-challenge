package com.goeuro.challenges.data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteStopsMapAggregator implements Supplier<RouteStopsMapAggregator> {

    private StopsAndRoutes routeStopsMapBuilder;

    public RouteStopsMapAggregator() {
        this.routeStopsMapBuilder = new StopsAndRoutes();
    }

    public void add(RouteWithStops routeWithStops) {
        this.routeStopsMapBuilder.add(routeWithStops);
    }

    public StopsAndRoutes finish() {
        return this.routeStopsMapBuilder.build();
    }

    @Override
    public RouteStopsMapAggregator get() {
        return new RouteStopsMapAggregator();
    }

    public RouteStopsMapAggregator sum(RouteStopsMapAggregator routeStopsMapAggregator) {

        Map<Integer, Set<Integer>> stopRoutesMap = routeStopsMapAggregator.routeStopsMapBuilder.getStopRoutesMap();


        Map<Integer, Set<Integer>> merged = Stream.of(stopRoutesMap, routeStopsMapBuilder.getStopRoutesMap())
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                    HashSet<Integer> both = new HashSet<>(a);
                    both.addAll(b);
                    return both;
                }));

        routeStopsMapBuilder.setRouteStops(merged);
        return this;
    }
}
