package io.github.odds4j.service;

import io.github.odds4j.model.Sport;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OddsService {

    private final SportsService sportsService;

    public Optional<List<Sport>> getSports() throws IOException, InterruptedException {
        return sportsService.getSports();
    }
}
