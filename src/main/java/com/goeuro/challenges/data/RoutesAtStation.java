package com.goeuro.challenges.data;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class RoutesAtStation {
    private static final int NUMBER_OF_STATIONS = 1_000_000;
    private static final int NUMBER_OF_BUS_ROUTES = 100_000;
    private static final int MAX_STATIONS_ON_A_ROUTE = 1000;
    private static final int AVERAGE_NUMBER_OF_ROUTES_PER_STOP =
            NUMBER_OF_BUS_ROUTES * MAX_STATIONS_ON_A_ROUTE / NUMBER_OF_STATIONS;
    private static final Path MAP_DB_STORE = Paths.get("routesAtStation.db");

    private Map<Integer, Set<Integer>> stationToRoutesMap;
    private DB db;

    public RoutesAtStation() {
        if(MAP_DB_STORE.toFile().exists()) {
            try {
                Files.delete(MAP_DB_STORE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        db = DBMaker
                .fileDB(MAP_DB_STORE.toFile())
                .fileMmapEnable()
                .make();

        stationToRoutesMap = (Map<Integer, Set<Integer>>) db
                .hashMap("stationToRoutesMap")
                .keySerializer(Serializer.INTEGER)
                .create();

    }

    void add(RouteWithStations routeWithStations) {
        Integer route = routeWithStations.getRoute();
        Set<Integer> stations = routeWithStations.getStations();
        stations.forEach(station -> {
            Set<Integer> routesAtStation = stationToRoutesMap.computeIfAbsent(station, k -> new HashSet<>
                    (AVERAGE_NUMBER_OF_ROUTES_PER_STOP));
            routesAtStation.add(route);
            stationToRoutesMap.put(station, routesAtStation);
        });
    }

    Map<Integer, Set<Integer>> getStationToRoutesMap() {
        return stationToRoutesMap;
    }

    void setRouteStations(Map<Integer, Set<Integer>> merged) {
        this.stationToRoutesMap = merged;
    }

    Optional<Set<Integer>> get(int stationSid) {
        return Optional.ofNullable(stationToRoutesMap.get(stationSid));
    }

    void close() throws IOException {
        db.close();
        Files.delete(MAP_DB_STORE);
    }
}
