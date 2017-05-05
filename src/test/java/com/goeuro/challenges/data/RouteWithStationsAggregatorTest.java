package com.goeuro.challenges.data;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class RouteWithStationsAggregatorTest {

    @Test
    public void shouldSumTwoAggregators() throws Exception {

        RouteWithStationsAggregator routeWithStationsAggregator = new RouteWithStationsAggregator();
        routeWithStationsAggregator.addStation(4).addStation(5).addStation(8);

        RouteWithStationsAggregator routeWithStationsAggregator2 = new RouteWithStationsAggregator();
        routeWithStationsAggregator.addStation(2).addStation(9).addStation(8);


        RouteWithStations routeWithStations = routeWithStationsAggregator.sum(routeWithStationsAggregator2).finish(10);

        assertThat(routeWithStations.getRoute(), is(equalTo(10)));
        assertThat(routeWithStations.getStations().size(), is(equalTo(5)));
        assertThat(routeWithStations.getStations().contains(4), is(true));
        assertThat(routeWithStations.getStations().contains(5), is(true));
        assertThat(routeWithStations.getStations().contains(8), is(true));
        assertThat(routeWithStations.getStations().contains(2), is(true));
        assertThat(routeWithStations.getStations().contains(9), is(true));
        assertThat(routeWithStations.getStations().contains(12), is(false));
    }
}