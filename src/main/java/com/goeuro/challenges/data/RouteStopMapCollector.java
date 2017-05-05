package com.goeuro.challenges.data;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class RouteStopMapCollector implements Collector<RouteWithStations, RouteWithStationsMapAggregator, RoutesAtStation> {
    @Override
    public Supplier<RouteWithStationsMapAggregator> supplier() {
        return RouteWithStationsMapAggregator::new;
    }

    @Override
    public BiConsumer<RouteWithStationsMapAggregator, RouteWithStations> accumulator() {
        return RouteWithStationsMapAggregator::add;
    }

    @Override
    public BinaryOperator<RouteWithStationsMapAggregator> combiner() {
        return RouteWithStationsMapAggregator::sum;
    }

    @Override
    public Function<RouteWithStationsMapAggregator, RoutesAtStation> finisher() {
        return RouteWithStationsMapAggregator::finish;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet(Characteristics.UNORDERED);
    }
}
