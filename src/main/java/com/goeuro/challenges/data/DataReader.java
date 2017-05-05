package com.goeuro.challenges.data;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class DataReader {

    private static final Logger LOG = getLogger(lookup().lookupClass());
    private String dataLocation;

    @Autowired
    public DataReader(@Value("${data.location}") String dataLocation) {
        this.dataLocation = dataLocation;
    }

    RoutesAtStation read() throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dataLocation))) {
            int lineCount = Integer.parseInt(bufferedReader.readLine());
            LOG.info("File contains {} routes", lineCount);

            return bufferedReader.lines()
                    .map(line -> Arrays.stream(line.split(" "))
                            .mapToInt(Integer::parseInt).boxed().collect(new RouteWithStationsCollector()))
                    .collect(new RouteStopMapCollector());
        } catch (IOException e) {
            LOG.error("Problem reading the route data.", e);
            throw e;
        }
    }
}
