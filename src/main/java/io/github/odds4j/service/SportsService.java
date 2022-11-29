package io.github.odds4j.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.odds4j.model.Sport;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SportsService {

    private final HttpService<List<Sport>> httpService = new HttpService<>();
    private final static String api = "https://api.the-odds-api.com/v4/sports/?apiKey=";
    private final String apiKey;

    public Optional<List<Sport>> getSports() throws IOException, InterruptedException {
        return Optional.of(httpService.makeRequest(URI.create(api + apiKey), new TypeReference<>() {
        }));
    }
}
