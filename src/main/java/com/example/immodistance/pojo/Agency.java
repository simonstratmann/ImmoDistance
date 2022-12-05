
package com.example.immodistance.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "phone",
    "url"
})
@ToString
public class Agency {

    @JsonProperty("name")
    public String name;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("url")
    public String url;

}
