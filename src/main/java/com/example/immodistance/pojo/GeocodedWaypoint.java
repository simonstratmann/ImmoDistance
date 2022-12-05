
package com.example.immodistance.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "geocoder_status",
    "place_id",
    "types"
})@ToString

public class GeocodedWaypoint {

    @JsonProperty("geocoder_status")
    public String geocoderStatus;
    @JsonProperty("place_id")
    public String placeId;
    @JsonProperty("types")
    public List<String> types = null;

}
