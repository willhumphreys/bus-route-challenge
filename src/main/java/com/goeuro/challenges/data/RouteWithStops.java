package com.goeuro.challenges.data;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

class RouteWithStops {

    private final int route;
    private final Set<Integer> stops;

    private RouteWithStops(int route, Set<Integer> stops) {
        this.route = route;
        this.stops = stops;
    }

    Integer getRoute() {
        return route;
    }

    Set<Integer> getStops() {
        return stops;
    }

    static class Builder {

        private final ImmutableSet.Builder<Integer> stops;
        private int route = -1;

        Builder() {
            this.stops = new ImmutableSet.Builder<>();
        }

        void setValue(Integer value) {
            if (route == -1) {
                this.route = value;
            } else {
                this.stops.add(value);
            }
        }

        RouteWithStops build() {
            return new RouteWithStops(route, stops.build());
        }

        ImmutableSet.Builder<Integer> getStops() {
            return stops;
        }
    }
}
