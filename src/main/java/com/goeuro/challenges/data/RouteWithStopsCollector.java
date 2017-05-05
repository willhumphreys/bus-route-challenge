package com.goeuro.challenges.data;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class RouteWithStopsCollector implements Collector<Integer, RouteWithStopsAggregator, RouteWithStops> {
    @Override
    public Supplier<RouteWithStopsAggregator> supplier() {
        return RouteWithStopsAggregator::new;
    }

    @Override
    public BiConsumer<RouteWithStopsAggregator, Integer> accumulator() {
        return RouteWithStopsAggregator::add;
    }

    @Override
    public BinaryOperator<RouteWithStopsAggregator> combiner() {
        return RouteWithStopsAggregator::sum;
    }

    @Override
    public Function<RouteWithStopsAggregator, RouteWithStops> finisher() {
        return RouteWithStopsAggregator::finish;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet(Characteristics.UNORDERED);
    }
}
