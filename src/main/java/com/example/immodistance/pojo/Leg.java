
package com.example.immodistance.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "arrival_time",
    "departure_time",
    "distance",
    "duration",
    "end_address",
    "end_location",
    "start_address",
    "start_location",
    "steps",
    "traffic_speed_entry",
    "via_waypoint"
})
@ToString
public class Leg {

    @JsonProperty("arrival_time")
    public ArrivalTime arrivalTime;
    @JsonProperty("departure_time")
    public DepartureTime departureTime;
    @JsonProperty("distance")
    public Distance distance;
    @JsonProperty("duration")
    public Duration duration;
    @JsonProperty("end_address")
    public String endAddress;
    @JsonProperty("end_location")
    public EndLocation endLocation;
    @JsonProperty("start_address")
    public String startAddress;
    @JsonProperty("start_location")
    public StartLocation startLocation;
    @JsonProperty("steps")
    public List<Step> steps = null;
    @JsonProperty("traffic_speed_entry")
    public List<Object> trafficSpeedEntry = null;
    @JsonProperty("via_waypoint")
    public List<Object> viaWaypoint = null;

}
