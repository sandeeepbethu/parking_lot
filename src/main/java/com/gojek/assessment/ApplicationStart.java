package com.gojek.assessment;

import com.gojek.assessment.service.CoreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationStart {

    private static CoreService coreService = null;

    public ApplicationStart(CoreService coreService) {
        this.coreService = coreService;
    }

    public static void main(String[] arguments) throws IOException {
        SpringApplication.run(ApplicationStart.class);
        coreService.loadInput();
    }
}
