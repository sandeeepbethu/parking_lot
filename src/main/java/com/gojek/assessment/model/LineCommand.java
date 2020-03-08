package com.gojek.assessment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LineCommand {
    public String command;
    public String dynamic_input;
    public Vehicle vehicle;
}
