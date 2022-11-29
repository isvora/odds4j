package io.github.odd4j;

import io.github.odds4j.Odds4jClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.org.webcompere.lightweightconfig.ConfigLoader;

class Odds4jClientTests {

    private Odds4jClient odds4jClient;
    Config configuration =
            ConfigLoader.loadYmlConfigFromResource("config.yml", Config.class);

    @BeforeEach
    void init() {
        odds4jClient = new Odds4jClient(configuration.getApiKey());
    }

    @Test
    void testGetSports() {
        var response = odds4jClient.getSports();

        Assertions.assertFalse(response.isEmpty());
    }
}
