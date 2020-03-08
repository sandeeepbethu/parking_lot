package com.gojek.assessment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Vehicle {
    public String color;
    public String registration_number;
    public int slot;
}
