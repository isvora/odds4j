package io.github.odds4j.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.odds4j.model.odds.Odds;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OddsService {

    private final HttpService<List<Odds>> httpService = new HttpService<>();
    private final static String api = "https://api.the-odds-api.com/v4/sports/";
    private final String apiKey;

    public Optional<List<Odds>> getOdds(String sport, String regions, String markets) throws IOException, InterruptedException {
        return Optional.of(httpService.makeRequest(URI.create(buildRequestUrl(sport, regions, markets)), new TypeReference<>() {
        }));
    }

    private String buildRequestUrl(String sport, String regions, String markets) {
        StringBuilder sb = new StringBuilder();
        sb.append(api);
        sb.append(sport);
        sb.append("/odds/?apiKey=");
        sb.append(apiKey);
        sb.append("&regions=");
        sb.append(regions);
        sb.append("&markets=");
        sb.append(markets);
        return sb.toString();
    }
}
