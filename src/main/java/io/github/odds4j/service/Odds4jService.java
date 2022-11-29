package io.github.odds4j.service;

import io.github.odds4j.model.historical.odds.HistoricalOdds;
import io.github.odds4j.model.odds.Odds;
import io.github.odds4j.model.scores.Match;
import io.github.odds4j.model.scores.Score;
import io.github.odds4j.model.sports.Sport;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public record Odds4jService(SportsService sportsService,
                            OddsService oddsService,
                            ScoresService scoresService,
                            HistoricalOddsService historicalOddsService) {

    public Optional<List<Sport>> getSports() throws IOException, InterruptedException {
        return sportsService.getSports();
    }

    public Optional<List<Odds>> getOdds(String sport, String regions, String markets) throws IOException, InterruptedException {
        return oddsService.getOdds(sport, regions, markets);
    }

    public Optional<List<Match>> getScores(String sport, Optional<Integer> daysFrom) throws IOException, InterruptedException {
        return scoresService.getScores(sport, daysFrom);
    }

    public Optional<HistoricalOdds> getHistoricalOdds(String sport, String regions, String markets) throws IOException, InterruptedException {
        return historicalOddsService.getHistoricalOdds(sport, regions, markets);
    }
}
