package com.goeuro.challenges;

import com.goeuro.challenges.data.DirectJourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DirectJourneyService {

    private DirectJourneyRepository directJourneyRepository;

    @Autowired
    public DirectJourneyService(DirectJourneyRepository directJourneyRepository) throws IOException {
        this.directJourneyRepository = directJourneyRepository;
    }

    boolean isDirect(Journey journey) {
        return directJourneyRepository.isDirect(journey);
    }
}
