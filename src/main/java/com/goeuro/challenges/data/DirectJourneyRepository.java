package com.goeuro.challenges.data;

import com.goeuro.challenges.Journey;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class DirectJourneyRepository {

    private final RoutesAtStation routesAtStation;

    public DirectJourneyRepository(DataReader dataReader) throws IOException {
        routesAtStation = dataReader.read();
    }

    public boolean isDirect(Journey journey) {
        return routesAtStation.isDirectRoute(journey);
    }
}
