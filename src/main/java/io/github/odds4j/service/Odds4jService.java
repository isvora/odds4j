package io.github.odds4j.service;

import io.github.odds4j.model.odds.Odds;
import io.github.odds4j.model.sports.Sport;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public record Odds4jService(SportsService sportsService, OddsService oddsService) {

    public Optional<List<Sport>> getSports() throws IOException, InterruptedException {
        return sportsService.getSports();
    }

    public Optional<List<Odds>> getOdds(String sport, String regions, String markets) throws IOException, InterruptedException {
        return oddsService.getOdds(sport, regions, markets);
    }
}
