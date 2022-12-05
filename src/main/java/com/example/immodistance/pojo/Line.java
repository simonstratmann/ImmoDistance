
package com.example.immodistance.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "agencies",
    "color",
    "short_name",
    "vehicle"
})

public class Line {

    @JsonProperty("agencies")
    public List<Agency> agencies = null;
    @JsonProperty("color")
    public String color;
    @JsonProperty("short_name")
    public String shortName;
    @JsonProperty("vehicle")
    public Vehicle vehicle;

}
