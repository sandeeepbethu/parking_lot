package com.gojek.assessment.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoreServiceTest {

    private CoreService coreService;

    @Before
    public void setup() {
        coreService = new CoreService();
    }

    @Test
    public void loadInput_Success() {
        String actual_output = coreService.loadInput();

        Assert.assertEquals("ok", actual_output);
    }
}
