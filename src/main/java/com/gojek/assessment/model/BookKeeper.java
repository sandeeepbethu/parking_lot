package com.gojek.assessment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class BookKeeper {
    public int capacity;
    public int free_slots;
    public List<Vehicle> vehicle_list;
}
