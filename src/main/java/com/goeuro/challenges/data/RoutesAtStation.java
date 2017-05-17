package com.goeuro.challenges.data;

import com.goeuro.challenges.Journey;
import com.koloboke.collect.set.IntSet;
import com.koloboke.collect.set.hash.HashIntSets;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RoutesAtStation {
    private static final int NUMBER_OF_STATIONS = 1_000_000;
    private static final int NUMBER_OF_BUS_ROUTES = 100_000;
    static final int MAX_STATIONS_ON_A_ROUTE = 1000;
    private static final int AVERAGE_NUMBER_OF_ROUTES_PER_STOP =
            NUMBER_OF_BUS_ROUTES * MAX_STATIONS_ON_A_ROUTE / NUMBER_OF_STATIONS;

    private Map<Integer, IntSet> stationToRoutesMap = new HashMap<>(NUMBER_OF_STATIONS);

    void add(RouteWithStations routeWithStations) {
        int route = routeWithStations.getRoute();
        Set<Integer> stations = routeWithStations.getStations();
        stations.forEach(station -> {
            IntSet routes = stationToRoutesMap.computeIfAbsent(
                    station, k -> HashIntSets.newMutableSet(AVERAGE_NUMBER_OF_ROUTES_PER_STOP));
            routes.add(route);
        });
    }

    Map<Integer, IntSet> getStationToRoutesMap() {
        return stationToRoutesMap;
    }

    void set(Map<Integer, IntSet> merged) {
        this.stationToRoutesMap = merged;
    }

    Optional<IntSet> get(int stationSid) {
        return Optional.ofNullable(stationToRoutesMap.get(stationSid));
    }

    boolean isDirectRoute(Journey journey) {

        return Stream.of(get(journey.getArrivalSid()), get(journey.getDepartureSid()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()))
                .entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }
}
