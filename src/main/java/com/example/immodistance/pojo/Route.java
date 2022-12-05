
package com.example.immodistance.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bounds",
    "copyrights",
    "legs",
    "overview_polyline",
    "summary",
    "warnings",
    "waypoint_order"
})
@ToString
public class Route {

    @JsonProperty("bounds")
    public Bounds bounds;
    @JsonProperty("copyrights")
    public String copyrights;
    @JsonProperty("legs")
    public List<Leg> legs = null;
    @JsonProperty("overview_polyline")
    public OverviewPolyline overviewPolyline;
    @JsonProperty("summary")
    public String summary;
    @JsonProperty("warnings")
    public List<String> warnings = null;
    @JsonProperty("waypoint_order")
    public List<Object> waypointOrder = null;

}
