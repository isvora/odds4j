package io.github.odds4j.model.sports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sport {

    private String key;

    private String group;

    private String title;

    private String description;

    private boolean active;

    @JsonProperty("has_outrights")
    private boolean hasOutrights;
}
