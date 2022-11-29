package io.github.odds4j;

import io.github.odds4j.model.historical.odds.HistoricalOdds;
import io.github.odds4j.model.odds.Odds;
import io.github.odds4j.model.scores.Match;
import io.github.odds4j.model.sports.Sport;
import io.github.odds4j.service.*;
import lombok.Getter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class Odds4jClient {

    private final String apiKey;
    private final Odds4jService odds4jService;

    public Odds4jClient(String apiKey) {
        this.apiKey = apiKey;
        this.odds4jService = new Odds4jService(
                new SportsService(apiKey),
                new OddsService(apiKey),
                new ScoresService(apiKey),
                new HistoricalOddsService(apiKey)
        );
    }

    public Odds4jClient(String apiKey, Odds4jService odds4jService) {
        this.apiKey = apiKey;
        this.odds4jService = odds4jService;
    }

    public List<Sport> getSports() {
        try {
            return odds4jService.getSports().orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            return Collections.emptyList();
        }
    }

    public List<Odds> getOdds(String sport, String regions, String markets) {
        try {
            return odds4jService.getOdds(sport, regions, markets).orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            return Collections.emptyList();
        }
    }

    public List<Match> getScores(String sport, Optional<Integer> daysFrom) {
        try {
            return odds4jService.getScores(sport, daysFrom).orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            return Collections.emptyList();
        }
    }

    public Optional<HistoricalOdds> getHistoricalOdds(String sport, String regions, String markets) {
        try {
            return odds4jService.getHistoricalOdds(sport, regions, markets);
        } catch (IOException | InterruptedException e) {
            return Optional.empty();
        }
    }

    public static Odds4jClientBuilder builder() {
        return new Odds4jClientBuilder();
    }

    public static class Odds4jClientBuilder {

        private String apiKey;

        public Odds4jClientBuilder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Odds4jClient build() {
            return new Odds4jClient(apiKey);
        }
    }
}
