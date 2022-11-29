package io.github.odds4j.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Service {

    private final String api = "https://api.the-odds-api.com/v4/sports/";

    private final String apiKey;

}
