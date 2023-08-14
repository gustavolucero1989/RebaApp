package com.reba.challenge.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Demography {
    String country;
    Double percentage;
}
