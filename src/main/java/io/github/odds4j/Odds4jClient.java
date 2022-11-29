package io.github.odds4j;

import io.github.odds4j.model.Sport;
import io.github.odds4j.service.OddsService;
import io.github.odds4j.service.SportsService;
import lombok.Getter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Getter
public class Odds4jClient {

    private final String apiKey;
    private final OddsService oddsService;

    public Odds4jClient(String apiKey) {
        this.apiKey = apiKey;
        this.oddsService = new OddsService(new SportsService(apiKey));
    }

    public List<Sport> getSports() {
        try {
            return oddsService.getSports().orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            return Collections.emptyList();
        }
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
