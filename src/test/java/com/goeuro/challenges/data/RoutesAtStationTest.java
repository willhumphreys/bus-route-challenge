package com.goeuro.challenges.data;

import com.goeuro.challenges.Journey;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoutesAtStationTest {


    private RoutesAtStation routesAtStation;

    @Before
    public void setUp() throws Exception {
        routesAtStation = new RoutesAtStation();
        routesAtStation.add(new RouteWithStations.Builder()
                .setRoute(10)
                .addStation(34)
                .addStation(50)
                .build());

        routesAtStation.add(new RouteWithStations.Builder()
                .setRoute(12)
                .addStation(34)
                .addStation(90)
                .build());
    }

    @Test
    public void shouldReturnTrueIfThereIsADirectRoute() throws Exception {
        assertThat(routesAtStation.get(
                new Journey(50, 34)), is(true));
    }

    @Test
    public void shouldReturnTrueIfThereIsAAnotherDirectRoute() throws Exception {
        assertThat(routesAtStation.get(
                new Journey(34, 90)), is(true));
    }

    @Test
    public void shouldReturnFalseIfThereIsNotADirectRoute() throws Exception {
        assertThat(routesAtStation.get(
                new Journey(10, 90)), is(false));
    }

    @Test
    public void shouldReturnFalseIfAStationDoesNotExist() throws Exception {
        assertThat(routesAtStation.get(
                new Journey(1, 90)), is(false));
    }

    @Test
    public void shouldReturnFalseIfBothStationsDoNotExist() throws Exception {
        assertThat(routesAtStation.get(
                new Journey(1, 2)), is(false));
    }
}