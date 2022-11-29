package io.github.odd4j;

import io.github.odds4j.Odds4jClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
}
