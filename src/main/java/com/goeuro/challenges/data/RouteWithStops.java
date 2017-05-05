package com.goeuro.challenges.data;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class RouteWithStops {

    private final int route;
    private final Set<Integer> stops;

    public RouteWithStops(int route, Set<Integer> stops) {
        this.route = route;
        this.stops = stops;
    }

    public Integer getRoute() {
        return route;
    }

    public Set<Integer> getStops() {
        return stops;
    }

    public static class Builder {

        private final ImmutableSet.Builder<Integer> stops;
        private int route = -1;

        public Builder() {
            this.stops = new ImmutableSet.Builder<>();
        }

        public void setValue(Integer value) {
            if (route == -1) {
                this.route = value;
            } else {
                this.stops.add(value);
            }
        }

        public RouteWithStops build() {
            return new RouteWithStops(route, stops.build());
        }

        public ImmutableSet.Builder<Integer> getStops() {
            return stops;
        }
    }
}
