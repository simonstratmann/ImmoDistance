
package com.example.immodistance.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "icon",
    "local_icon",
    "name",
    "type"
})
@ToString
public class Vehicle {

    @JsonProperty("icon")
    public String icon;
    @JsonProperty("local_icon")
    public String localIcon;
    @JsonProperty("name")
    public String name;
    @JsonProperty("type")
    public String type;

}
