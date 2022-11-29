package io.github.odd4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.odds4j.Odds4jClient;
import io.github.odds4j.model.historical.odds.HistoricalOdds;
import io.github.odds4j.service.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.org.webcompere.lightweightconfig.ConfigLoader;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class Odds4jClientTests {

    private Odds4jClient odds4jClient;
    @Mock
    private HistoricalOddsService historicalOddsService;
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

    @Test
    void testGetScores() {
        String sport = "basketball_nba";
        Optional<Integer> daysFrom = Optional.of(1);

        var response = odds4jClient.getScores(sport, daysFrom);

        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void testGetScoresWithoutDaysFrom() {
        String sport = "basketball_nba";

        var response = odds4jClient.getScores(sport, Optional.empty());

        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    @SneakyThrows
    void testGetHistoricalOdds(){
        var apiKey = configuration.getApiKey();
        Odds4jService odds4jService = new Odds4jService(new SportsService(apiKey),
                new OddsService(apiKey), new ScoresService(apiKey), historicalOddsService);
        odds4jClient = new Odds4jClient(configuration.getApiKey(), odds4jService);

        File file = new File(
                Objects.requireNonNull(this.getClass().getClassLoader().getResource("historical_odds.json")).getFile()
        );
        ObjectMapper mapper = new ObjectMapper();
        HistoricalOdds historicalOdds = mapper.readValue(file, HistoricalOdds.class);

        String sport = "americanfootball_ncaaf";
        String region = "us";
        String markets = "h2h";

        Mockito.when(historicalOddsService.getHistoricalOdds(sport, region, markets)).thenReturn(Optional.of(historicalOdds));

        var response = odds4jClient.getHistoricalOdds(sport, region, markets);

        Assertions.assertTrue(response.isPresent());
        Assertions.assertFalse(response.get().getData().isEmpty());
    }
}
