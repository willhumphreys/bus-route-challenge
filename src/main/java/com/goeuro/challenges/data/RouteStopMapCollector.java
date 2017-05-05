package com.goeuro.challenges.data;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class RouteStopMapCollector implements Collector<RouteWithStops, RouteStopsMapAggregator, RoutesAtStation> {
    @Override
    public Supplier<RouteStopsMapAggregator> supplier() {
        return RouteStopsMapAggregator::new;
    }

    @Override
    public BiConsumer<RouteStopsMapAggregator, RouteWithStops> accumulator() {
        return RouteStopsMapAggregator::add;
    }

    @Override
    public BinaryOperator<RouteStopsMapAggregator> combiner() {
        return RouteStopsMapAggregator::sum;
    }

    @Override
    public Function<RouteStopsMapAggregator, RoutesAtStation> finisher() {
        return RouteStopsMapAggregator::finish;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet(Characteristics.UNORDERED);
    }
}
