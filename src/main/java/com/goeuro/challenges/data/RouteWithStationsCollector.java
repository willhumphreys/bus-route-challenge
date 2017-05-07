package com.goeuro.challenges.data;

import com.google.common.collect.Sets;
import org.slf4j.Logger;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

class RouteWithStationsCollector implements Collector<Integer, RouteWithStationsAggregator, RouteWithStations> {
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private Integer route;

    @Override
    public Supplier<RouteWithStationsAggregator> supplier() {
        return new RouteWithStationsAggregator();
    }

    @Override
    public BiConsumer<RouteWithStationsAggregator, Integer> accumulator() {
        return RouteWithStationsAggregator::addStation;
    }

    @Override
    public BinaryOperator<RouteWithStationsAggregator> combiner() {
        return RouteWithStationsAggregator::sum;
    }

    @Override
    public Function<RouteWithStationsAggregator, RouteWithStations> finisher() {
        return routeWithStationsAggregator -> routeWithStationsAggregator.finish(route);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet(Characteristics.UNORDERED);
    }

    void setRoute(Integer route) {
        LOG.info("Reading route {}", route);
        this.route = route;
    }
}
