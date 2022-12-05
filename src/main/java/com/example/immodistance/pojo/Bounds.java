
package com.example.immodistance.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "northeast",
    "southwest"
})
@ToString
public class Bounds {

    @JsonProperty("northeast")
    public Northeast northeast;
    @JsonProperty("southwest")
    public Southwest southwest;

}
