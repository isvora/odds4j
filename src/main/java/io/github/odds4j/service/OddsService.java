package io.github.odds4j.service;

import io.github.odds4j.model.Sport;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public record OddsService(SportsService sportsService) {

    public Optional<List<Sport>> getSports() throws IOException, InterruptedException {
        return sportsService.getSports();
    }
}
