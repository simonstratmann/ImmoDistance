
package com.example.immodistance.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "distance",
    "duration",
    "end_location",
    "html_instructions",
    "polyline",
    "start_location",
    "steps",
    "travel_mode",
    "transit_details"
})
@ToString
public class Step {

    @JsonProperty("distance")
    public Distance distance;
    @JsonProperty("duration")
    public Duration duration;
    @JsonProperty("end_location")
    public EndLocation endLocation;
    @JsonProperty("html_instructions")
    public String htmlInstructions;
    @JsonProperty("polyline")
    public Polyline polyline;
    @JsonProperty("start_location")
    public StartLocation startLocation;
    @JsonProperty("steps")
    public List<Step> steps = null;
    @JsonProperty("travel_mode")
    public String travelMode;
    @JsonProperty("transit_details")
    public TransitDetails transitDetails;

}
