
package com.example.immodistance.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "geocoded_waypoints",
    "routes",
    "status"
})

@ToString
public class Direction {

    @JsonProperty("geocoded_waypoints")
    public List<GeocodedWaypoint> geocodedWaypoints = null;
    @JsonProperty("routes")
    public List<Route> routes = null;
    @JsonProperty("status")
    public String status;

}
