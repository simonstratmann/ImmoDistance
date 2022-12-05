
package com.example.immodistance.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "text",
    "time_zone",
    "value"
})
@ToString
public class DepartureTime {

    @JsonProperty("text")
    public String text;
    @JsonProperty("time_zone")
    public String timeZone;
    @JsonProperty("value")
    public Integer value;

}
