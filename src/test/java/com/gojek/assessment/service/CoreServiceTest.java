package com.gojek.assessment.service;

import com.gojek.assessment.common.StaticReferences;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CoreServiceTest {

    private CoreService coreService;

    @Before
    public void setup() {
        coreService = new CoreService();
    }

    @Test
    public void loadInput_Success() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(StaticReferences.FILE_INPUT.getBytes());
        System.setIn(inputStream);

        String actual_output = coreService.loadInput();

        Assert.assertEquals("success", actual_output);
    }

    @Test
    public void loadInput_Fail() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        System.setIn(inputStream);

        String actual_output = coreService.loadInput();

        Assert.assertEquals("fail", actual_output);
    }
}
