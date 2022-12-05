
package com.example.immodistance.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "arrival_stop",
    "arrival_time",
    "departure_stop",
    "departure_time",
    "headsign",
    "line",
    "num_stops"
})@ToString

public class TransitDetails {

    @JsonProperty("arrival_stop")
    public ArrivalStop arrivalStop;
    @JsonProperty("arrival_time")
    public ArrivalTime arrivalTime;
    @JsonProperty("departure_stop")
    public DepartureStop departureStop;
    @JsonProperty("departure_time")
    public DepartureTime departureTime;
    @JsonProperty("headsign")
    public String headsign;
    @JsonProperty("line")
    public Line line;
    @JsonProperty("num_stops")
    public Integer numStops;

}
