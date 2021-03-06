package com.gojek.assessment.service;

import com.gojek.assessment.common.StaticReferences;
import com.gojek.assessment.model.LineCommand;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CoreServiceTest {

    private CoreService coreService;
    private CommandInterpreter commandInterpreter;


    @Before
    public void setup() {
        commandInterpreter = mock(CommandInterpreter.class);
        coreService = new CoreService(commandInterpreter);
    }

    @Test
    public void loadInput_Success() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(StaticReferences.FILE_INPUT_SUCCESS.getBytes());
        System.setIn(inputStream);

        List<LineCommand> lineCommandList = mock(ArrayList.class);
        doNothing().when(commandInterpreter).executeFileCommands(lineCommandList);

        coreService.loadInput();
    }

    @Test
    public void loadInput_SingularCommand() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("create_parking_lot 1".getBytes());
        System.setIn(inputStream);

        List<LineCommand> lineCommandList = mock(ArrayList.class);
        doNothing().when(commandInterpreter).executeFileCommands(lineCommandList);

        coreService.loadInput();
    }

    @Test
    public void loadInput_IncorrectCommands() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(StaticReferences.FILE_INPUT_INCORRECT_COMMANDS.getBytes());
        System.setIn(inputStream);

        List<LineCommand> lineCommandList = mock(ArrayList.class);
        doNothing().when(commandInterpreter).executeFileCommands(lineCommandList);

        coreService.loadInput();
    }

    @Test
    public void loadInput_BlankLines() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(StaticReferences.FILE_INPUT_BLANK_LINES.getBytes());
        System.setIn(inputStream);

        List<LineCommand> lineCommandList = mock(ArrayList.class);
        doNothing().when(commandInterpreter).executeFileCommands(lineCommandList);

        coreService.loadInput();
    }

    @Test
    public void loadInput_Fail() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        System.setIn(inputStream);

        coreService.loadInput();
    }

    @Test
    public void loadInput_Empty() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(StaticReferences.FILE_EMPTY.getBytes());
        System.setIn(inputStream);

        coreService.loadInput();
    }
}
