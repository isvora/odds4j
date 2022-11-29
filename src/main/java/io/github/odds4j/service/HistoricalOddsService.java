package io.github.odds4j.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.odds4j.model.historical.odds.HistoricalOdds;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class HistoricalOddsService extends Service {

    private final HttpService<HistoricalOdds> httpService = new HttpService<>();

    public HistoricalOddsService(String apiKey) {
        super(apiKey);
    }

    public Optional<HistoricalOdds> getHistoricalOdds(String sport, String region, String markets) throws IOException, InterruptedException {
        return Optional.of(httpService.makeRequest(URI.create(buildRequestUrl(sport, region, markets)), new TypeReference<>() {}));
    }

    private String buildRequestUrl(String sport, String regions, String markets) {
        StringBuilder sb = new StringBuilder();
        sb.append(getApi());
        sb.append(sport);
        sb.append("/odds-history/?apiKey=");
        sb.append(getApiKey());
        sb.append("&regions=");
        sb.append(regions);
        sb.append("&markets=");
        sb.append(markets);
        return sb.toString();
    }
}
