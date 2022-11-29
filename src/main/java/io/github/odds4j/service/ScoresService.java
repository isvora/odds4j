package io.github.odds4j.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.odds4j.model.scores.Match;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

public class ScoresService extends Service {

    private final HttpService<List<Match>> httpService = new HttpService<>();

    public ScoresService(String apiKey) {
        super(apiKey);
    }

    public Optional<List<Match>> getScores(String sport, Optional<Integer> daysFrom) throws IOException, InterruptedException {
        return Optional.of(httpService.makeRequest(URI.create(buildRequestUrl(sport, daysFrom)), new TypeReference<>(){}));
    }

    private String buildRequestUrl(String sport, Optional<Integer> daysFrom) {
        StringBuilder sb = new StringBuilder();
        sb.append(getApi());
        sb.append(sport);
        sb.append("/scores/");
        if (daysFrom.isPresent()) {
            sb.append("?daysFrom=");
            sb.append(daysFrom.get());
            sb.append("&");
        } else {
            sb.append("?");
        }
        sb.append("apiKey=");
        sb.append(getApiKey());
        return sb.toString();
    }
}
