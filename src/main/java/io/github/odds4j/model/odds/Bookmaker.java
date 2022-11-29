package io.github.odds4j.model.odds;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.List;

@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bookmaker {

    private String key;

    private String title;

    private List<Market> markets;

    @JsonProperty("last_update")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date lastUpdate;

}
