package io.github.odd4j;

import io.github.odds4j.Odds4jClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.org.webcompere.lightweightconfig.ConfigLoader;

class Odds4jClientTests {

    private Odds4jClient odds4jClient;
    private final Config configuration =
            ConfigLoader.loadYmlConfigFromResource("config.yml", Config.class);

    @BeforeEach
    void init() {
        odds4jClient = Odds4jClient.builder().apiKey(configuration.getApiKey()).build();
    }

    @Test
    void testGetSports() {
        var response = odds4jClient.getSports();

        Assertions.assertFalse(response.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"americanfootball_nfl", "americanfootball_ncaaf"})
    void testGetOdds(String sport) {
        String region = "us";
        String markets = "h2h";

        var response = odds4jClient.getOdds(sport, region, markets);

        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void testGetOddsInvalidMarket() {
        String sport = "americanfootball_ncaaf";
        String region = "us";
        String markets = "invalid";

        var response = odds4jClient.getOdds(sport, region, markets);

        Assertions.assertTrue(response.isEmpty());
    }
}
