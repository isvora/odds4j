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

    /**
     * Used to get a list of all the sports currently supported by the-odds-api
     * @return List of Sport objects
     */
    public List<Sport> getSports() {
        try {
            return odds4jService.getSports().orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Get all available odds for a given sport, in a region specified and for a market specified.
     * @param sport the sport key you get by calling getSports() method. Example: "americanfootball_ncaaf"
     * @param regions the region you want to get results from. Example "us"
     * @param markets the markets to get odds for. Example "h2h"
     * @return
     */
    public List<Odds> getOdds(String sport, String regions, String markets) {
        try {
            return odds4jService.getOdds(sport, regions, markets).orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Gets the scored for a given sport. Optionally you can add daysFrom to get historical scores from the last 3 days
     * If you don't specify daysFrom, you will get live data only.
     * @param sport the sport key you get by calling getSports() method. Example: "americanfootball_ncaaf"
     * @param daysFrom integer (1 to 3)
     * @return
     */
    public List<Match> getScores(String sport, Optional<Integer> daysFrom) {
        try {
            return odds4jService.getScores(sport, daysFrom).orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Get all available historical odds for a given sport, in a region specified and for a market specified.
     * In order to use this, you need to have a paid subscription to the-odds-api.
     * @param sport the sport key you get by calling getSports() method. Example: "americanfootball_ncaaf"
     * @param regions the region you want to get results from. Example "us"
     * @param markets the markets to get odds for. Example "h2h"
     * @return
     */

    public Optional<HistoricalOdds> getHistoricalOdds(String sport, String regions, String markets) {
        try {
            return odds4jService.getHistoricalOdds(sport, regions, markets);
        } catch (IOException | InterruptedException e) {
            return Optional.empty();
        }
    }

    /**
     * Method used to create a builder instance
     * @return Odds4jClientBuilder instance
     */
    public static Odds4jClientBuilder builder() {
        return new Odds4jClientBuilder();
    }

    public static class Odds4jClientBuilder {

        private String apiKey;

        /**
         * Method used to store the api key into your Odds4jClient.
         * @param apiKey
         * @return instance of Odds4jClientBuilder with api key set.
         */
        public Odds4jClientBuilder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Odds4jClient build() {
            return new Odds4jClient(apiKey);
        }
    }
}
