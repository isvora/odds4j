package io.github.odds4j.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.odds4j.model.sports.Sport;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

public class SportsService extends Service {

    private final HttpService<List<Sport>> httpService = new HttpService<>();

    public SportsService(String apiKey) {
        super(apiKey);
    }

    public Optional<List<Sport>> getSports() throws IOException, InterruptedException {
        return Optional.of(httpService.makeRequest(URI.create(buildRequestUrl()), new TypeReference<>() {
        }));
    }

    private String buildRequestUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(getApi());
        sb.append("?apiKey=");
        sb.append(getApiKey());
        return sb.toString();
    }
}
