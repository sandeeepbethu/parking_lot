package com.gojek.assessment.service;

import com.gojek.assessment.model.BookKeeper;
import com.gojek.assessment.model.LineCommand;
import com.gojek.assessment.model.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandInterpreterTest {

    private BookKeeper bookKeeper;
    private CommandInterpreter commandInterpreter;

    @Before
    public void setup() {
        bookKeeper = new BookKeeper();
        commandInterpreter = new CommandInterpreter(bookKeeper);
    }

    @Test
    public void executeFileCommands_CreateParkingLot_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_Park_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));
        lineCommandList.add(park());

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_ParkingFull_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("1"));
        lineCommandList.add(park());
        lineCommandList.add(park());

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_ParkMultiple_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));
        lineCommandList.add(park());
        lineCommandList.add(park());

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_DuplicateParkingLotCreation_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));
        lineCommandList.add(createParking("2"));

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_GetRegNumbersByColor_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));
        lineCommandList.add(park());

        LineCommand lineCommand = new LineCommand();
        lineCommand.setCommand("REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR");
        lineCommand.setDynamic_input("White");
        lineCommandList.add(lineCommand);

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_GetSlotsByColor_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));
        lineCommandList.add(park());

        LineCommand lineCommand = new LineCommand();
        lineCommand.setCommand("SLOT_NUMBERS_FOR_CARS_WITH_COLOUR");
        lineCommand.setDynamic_input("White");
        lineCommandList.add(lineCommand);

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_GetSlotsByRegistrationNumber_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));
        lineCommandList.add(park());

        LineCommand lineCommand = new LineCommand();
        lineCommand.setCommand("SLOT_NUMBER_FOR_REGISTRATION_NUMBER");
        lineCommand.setDynamic_input("KA-01-HH-1234");
        lineCommandList.add(lineCommand);

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_Status_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));
        lineCommandList.add(park());
        LineCommand lineCommand = new LineCommand();
        lineCommand.setCommand("STATUS");
        lineCommandList.add(lineCommand);

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    @Test
    public void executeFileCommands_Leave_Success() {
        List<LineCommand> lineCommandList = new ArrayList<>();
        lineCommandList.add(createParking("2"));
        lineCommandList.add(park());
        LineCommand lineCommand = new LineCommand();
        lineCommand.setCommand("LEAVE");
        lineCommand.setDynamic_input("1");
        lineCommandList.add(lineCommand);

        commandInterpreter.executeFileCommands(lineCommandList);
    }

    // Re usable content
    private LineCommand createParking(String size) {
        LineCommand lineCommand = new LineCommand();
        lineCommand.setCommand("CREATE_PARKING_LOT");
        lineCommand.setDynamic_input(size);
        return lineCommand;
    }

    private LineCommand park() {
        LineCommand lineCommand = new LineCommand();
        lineCommand.setCommand("PARK");
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistration_number("KA-01-HH-1234");
        vehicle.setColor("White");
        lineCommand.setVehicle(vehicle);
        return lineCommand;
    }
}
