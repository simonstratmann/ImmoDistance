
package com.example.immodistance.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "location",
    "name"
})
@ToString
public class DepartureStop {

    @JsonProperty("location")
    public Location location;
    @JsonProperty("name")
    public String name;

}
