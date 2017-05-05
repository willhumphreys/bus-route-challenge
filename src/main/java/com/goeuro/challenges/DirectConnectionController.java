package com.goeuro.challenges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectConnectionController {

    private DirectJourneyService directJourneyService;

    @Autowired
    public DirectConnectionController(DirectJourneyService directJourneyService) {
        this.directJourneyService = directJourneyService;
    }

    @RequestMapping("/api/direct")
    public DirectJourney isDirect(@RequestParam(value = "dep_sid") int depSid,
                                  @RequestParam(value = "arr_sid") int arrSid) {

        boolean direct = directJourneyService.isDirect(new Journey(depSid, arrSid));
        return new DirectJourney(depSid, arrSid, direct);
    }
}
