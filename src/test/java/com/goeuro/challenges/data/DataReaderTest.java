package com.goeuro.challenges.data;

import com.koloboke.collect.set.IntSet;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DataReaderTest {

    private RoutesAtStation routesAtStation;

    @Before
    public void setUp() throws Exception {
        DataReader dataReader = new DataReader("data/example");
        routesAtStation = dataReader.read();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldReturnRoutesAtAStop150() throws Exception {
        IntSet routesAtStop = routesAtStation.get(150).get();
        assertThat(routesAtStop.size(), is(equalTo(4)));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldReturnRoutesAtAStop121() throws Exception {
        IntSet routesAtStop = routesAtStation.get(121).get();
        assertThat(routesAtStop.size(), is(equalTo(3)));
    }

    @Test
    public void shouldReturnNoRoutesIfTheStopDoesNotExist() throws Exception {
        Optional<IntSet> routesAtStop = routesAtStation.get(3333);
        assertThat(routesAtStop.isPresent(), is(false));
    }

    @Test(expected = IOException.class)
    public void shouldThrowAnExceptionIfThereIsAnIOException() throws Exception {
        DataReader dataReader = new DataReader("invalidLocation");
        routesAtStation = dataReader.read();
    }
}