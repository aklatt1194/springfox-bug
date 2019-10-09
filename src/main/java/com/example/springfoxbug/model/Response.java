package com.example.springfoxbug.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel
@Builder
@Value
public class Response {
    @ApiModelProperty(value = "This little latlong")
    LatLong latLong;
    @ApiModelProperty(value = "dubble U")
    Double dub;
}
