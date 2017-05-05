package com.goeuro.challenges.data;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class RouteWithStationsMapAggregatorTest {

    @Test
    public void shouldSumTwoAggregators() throws Exception {

        RouteWithStationsMapAggregator routeWithStationsMapAggregator = new RouteWithStationsMapAggregator();

        routeWithStationsMapAggregator.add(new RouteWithStations.Builder().setRoute(3)
                .addStation(5).build());

        RouteWithStationsMapAggregator routeWithStationsMapAggregator2 = new RouteWithStationsMapAggregator();
        routeWithStationsMapAggregator2.add(new RouteWithStations.Builder().setRoute(4)
                .addStation(8).addStation(9).addStation(5).build());

        routeWithStationsMapAggregator.sum(routeWithStationsMapAggregator2);
        RoutesAtStation routesAtStation = routeWithStationsMapAggregator.finish();
        Set<Integer> routesAtStop9 = routesAtStation.get(9).get();

        assertThat(routesAtStop9.size(), is(equalTo(1)));
        assertThat(routesAtStop9.contains(4), is(true));

        Set<Integer> routesAtStop5 = routesAtStation.get(5).get();
        assertThat(routesAtStop5.size(), is(equalTo(2)));
        assertThat(routesAtStop5.contains(3), is(true));
        assertThat(routesAtStop5.contains(4), is(true));

        assertThat(routesAtStation.get(100).isPresent(), is(false));
    }
}