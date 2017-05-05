package com.goeuro.challenges.data;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class RouteWithStationsCollector implements Collector<Integer, RouteWithStationsAggregator, RouteWithStations> {

    @Override
    public Supplier<RouteWithStationsAggregator> supplier() {
        return RouteWithStationsAggregator::new;
    }

    @Override
    public BiConsumer<RouteWithStationsAggregator, Integer> accumulator() {
        return RouteWithStationsAggregator::addData;
    }

    @Override
    public BinaryOperator<RouteWithStationsAggregator> combiner() {
        return RouteWithStationsAggregator::sum;
    }

    @Override
    public Function<RouteWithStationsAggregator, RouteWithStations> finisher() {
        return RouteWithStationsAggregator::finish;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet(Characteristics.UNORDERED);
    }
}
