# odds4j
Oddj4j is a Java client https://the-odds-api.com api. 

# Getting your api key

You can get your api key from [here](https://the-odds-api.com/#get-access).

# Getting started

First thing we need is to create an instance of `Odds4jClient`

```java
import io.github.odds4j.*;

public class Main {
    
    public static void main(String... args) {
        Odds4jClient odds4jClient = Odds4jClient.builder()
                .apiKey("YOUR_API_KEY")
                .build();
    }
    
}
```

Replace `YOUR_API_KEY` with your actual api key that is provided by the-odds-api.

From there, we can use the `odds4jClient` object to query the-odds-api database.

```java
import io.github.odds4j.*;
import io.github.odds4j.model.historical.odds.HistoricalOdds;
import io.github.odds4j.model.odds.Odds;
import io.github.odds4j.model.scores.Score;
import io.github.odds4j.model.sports.Sport;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String... args) {
        Odds4jClient odds4jClient = Odds4jClient.builder()
                .apiKey("YOUR_API_KEY")
                .build();

        // Returns a list of sports
        List<Sport> sports = odds4jClient.getSports();

        // Returns a list of Odds for a given sport/region/market
        List<Odds> odds = odds4jClient.getOdds("americanfootball_ncaaf", "us", "h2h");

        // Returns a list of scores for a given sport
        List<Score> scores = odds4jClient.getScores("americanfootball_ncaaf", Optional.of(1)); 

        // This requires a paid subscription to use!
        // Returns a list of Historical Odds for a given sport/region/market
        Optional<HistoricalOdds> historicalOdds = odds4jClient.getHistoricalOdds("americanfootball_ncaaf", "us", "h2h");
    }

}
```
