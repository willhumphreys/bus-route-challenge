package com.goeuro.challenges.data;

import com.goeuro.challenges.Journey;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Repository
public class DirectJourneyRepository {

    private final StopsAndRoutes stopsAndRoutes;

    public DirectJourneyRepository(DataReader dataReader) throws IOException {
        stopsAndRoutes = dataReader.read();
    }

    public boolean isDirect(Journey journey) {
        Optional<Set<Integer>> routesAtDeparture = stopsAndRoutes.getRoutesAtStop(journey.getDepartureSid());
        if(!routesAtDeparture.isPresent()) {
            return false;
        }

        Optional<Set<Integer>> routesAtArrivals = stopsAndRoutes.getRoutesAtStop(journey.getArrivalSid());
        if(!routesAtArrivals.isPresent()) {
            return false;
        }

        Sets.SetView<Integer> intersection = Sets.intersection(routesAtDeparture.get(), routesAtArrivals.get());
        return !intersection.isEmpty();
    }
}
