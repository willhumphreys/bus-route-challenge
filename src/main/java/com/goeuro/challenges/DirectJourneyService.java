package com.goeuro.challenges;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class DirectJourneyService {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    public boolean isDirect(Journey journey) {
        return true;
    }


}
