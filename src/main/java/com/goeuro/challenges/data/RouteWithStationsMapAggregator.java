package com.goeuro.challenges.data;

import com.koloboke.collect.set.IntSet;
import com.koloboke.collect.set.hash.HashIntSet;
import com.koloboke.collect.set.hash.HashIntSets;

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

        Map<Integer, IntSet> stopRoutesMap = routeWithStationsMapAggregator.routesAtStation.getMap();

        routesAtStation.setRouteStations(mergeStationRoutesMaps(stopRoutesMap));
        return this;
    }

    private Map<Integer, IntSet> mergeStationRoutesMaps(Map<Integer, IntSet> stopRoutesMap) {
        return Stream.of(stopRoutesMap, routesAtStation.getMap())
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                    HashIntSet both = HashIntSets.newMutableSet(a);
                    both.addAll(b);
                    return both;
                }));
    }
}
