package io.github.odds4j.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.odds4j.model.odds.Odds;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

public class OddsService extends Service {

    private final HttpService<List<Odds>> httpService = new HttpService<>();

    public OddsService(String apiKey) {
        super(apiKey);
    }

    public Optional<List<Odds>> getOdds(String sport, String regions, String markets) throws IOException, InterruptedException {
        return Optional.of(httpService.makeRequest(URI.create(buildRequestUrl(sport, regions, markets)), new TypeReference<>() {
        }));
    }

    private String buildRequestUrl(String sport, String regions, String markets) {
        StringBuilder sb = new StringBuilder();
        sb.append(getApi());
        sb.append(sport);
        sb.append("/odds/?apiKey=");
        sb.append(getApiKey());
        sb.append("&regions=");
        sb.append(regions);
        sb.append("&markets=");
        sb.append(markets);
        return sb.toString();
    }
}
