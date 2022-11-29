package io.github.odds4j.model.historical.odds;

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
public class HistoricalOdds {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date timestamp;

    @JsonProperty("previous_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date previousTimestamp;

    @JsonProperty("next_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date nextTimestamp;

    private List<Data> data;
}
