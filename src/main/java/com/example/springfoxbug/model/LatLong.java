package com.example.springfoxbug.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class LatLong {
    Double lat;
    Double lng;
}
