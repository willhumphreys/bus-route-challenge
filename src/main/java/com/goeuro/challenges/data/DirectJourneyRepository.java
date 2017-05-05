package com.goeuro.challenges.data;

import com.goeuro.challenges.Journey;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Repository
public class DirectJourneyRepository {

    private final RoutesAtStation routesAtStation;

    public DirectJourneyRepository(DataReader dataReader) throws IOException {
        routesAtStation = dataReader.read();
    }

    public boolean isDirect(Journey journey) {
        Optional<Set<Integer>> routesAtDeparture = routesAtStation.get(journey.getDepartureSid());
        if(!routesAtDeparture.isPresent()) {
            return false;
        }

        Optional<Set<Integer>> routesAtArrivals = routesAtStation.get(journey.getArrivalSid());
        if(!routesAtArrivals.isPresent()) {
            return false;
        }

        Sets.SetView<Integer> intersection = Sets.intersection(routesAtDeparture.get(), routesAtArrivals.get());
        return !intersection.isEmpty();
    }
}
