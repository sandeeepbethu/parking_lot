package com.gojek.assessment.service;

import com.gojek.assessment.common.StaticReferences;
import com.gojek.assessment.model.BookKeeper;
import com.gojek.assessment.model.LineCommand;
import com.gojek.assessment.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandInterpreter {

    private final BookKeeper bookKeeper;

    public CommandInterpreter(BookKeeper bookKeeper) {
        this.bookKeeper = bookKeeper;
    }

    private List<Integer> availableSlots = new ArrayList<>();
    private List<Vehicle> runTimeList;

    public void executeFileCommands(List<LineCommand> lineCommandList) {
        for (LineCommand lineCommand:lineCommandList) {
            switch (lineCommand.getCommand()){
                case StaticReferences
                        .CREATE_PARKING_LOT :
                    bookKeeper.setCapacity(Integer.parseInt(lineCommand.getDynamic_input()));
                    bookKeeper.setFree_slots(Integer.parseInt(lineCommand.getDynamic_input()));
                    System.out.println("Created a parking lot with : " + lineCommand.getDynamic_input() + " slots");
                    slotSequenceGenerator();
                    break;
                case StaticReferences
                        .PARK:
                    allocateSlot(lineCommand.getVehicle());
                    break;
                case StaticReferences
                        .LEAVE:
                    freeUpSlot(Integer.parseInt(lineCommand.getDynamic_input()));
                    break;
                case StaticReferences
                        .STATUS:
                    printStatus();
                    break;
                case StaticReferences
                        .REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                    registrationNumbersByColour(lineCommand.getDynamic_input(), "reg_numbers");
                    break;
                case StaticReferences
                        .SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                    registrationNumbersByColour(lineCommand.getDynamic_input(), "slots");
                    break;
                case StaticReferences
                        .SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                    slotNumberByRegistration(lineCommand.getDynamic_input());
                    break;
                default: break;
            }
        }
    }

    private void slotNumberByRegistration(String number) {
        List<Vehicle> vehicleList = bookKeeper.getVehicle_list().stream().filter(veh->veh.getRegistration_number().equalsIgnoreCase(number)).collect(Collectors.toList());
        if(vehicleList != null && !vehicleList.isEmpty())
            System.out.println(vehicleList.get(0).getSlot());
        else
            System.out.println("Not found");
    }

    private void registrationNumbersByColour(String colour, String fetch) {
        List<Vehicle> vehicleList = bookKeeper.getVehicle_list().stream().filter(veh->veh.getColor().equalsIgnoreCase(colour)).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();

        if(fetch.equalsIgnoreCase("reg_numbers")) {
            for (Vehicle vehicle:
                    vehicleList) {
                stringBuilder.append(vehicle.getRegistration_number());
                stringBuilder.append(", ");
            }
        } else {
            for (Vehicle vehicle:
                    vehicleList) {
                stringBuilder.append(vehicle.getSlot());
                stringBuilder.append(", ");
            }
        }

        System.out.println(stringBuilder.toString());
    }

    private void allocateSlot(Vehicle vehicle) {
            if(bookKeeper.getVehicle_list() == null) {
                vehicle.setSlot(availableSlots.get(0));
                bookKeeper.setVehicle_list(Arrays.asList( vehicle));
                System.out.println("Allocated slot number: " + 1);
                availableSlots.remove(0);
            } else {
                List<Integer> occupiedSlotNumbers = bookKeeper.getVehicle_list().stream().map(veh -> veh.getSlot()).collect(Collectors.toList());
                if(occupiedSlotNumbers.size() == bookKeeper.getCapacity()) {
                    System.out.println("Sorry, parking lot is full");
                } else {
                    vehicle.setSlot(availableSlots.get(0));
                    runTimeList = new ArrayList<>();
                    runTimeList.addAll(bookKeeper.getVehicle_list());
                    runTimeList.add(vehicle);
                    bookKeeper.setVehicle_list(runTimeList);
                    System.out.println("Allocated slot number: " + availableSlots.get(0));
                    availableSlots.remove(0);
                }
            }
    }

    private void slotSequenceGenerator() {
        for(int loop_count = 1; loop_count <= bookKeeper.getCapacity(); loop_count ++){
            availableSlots.add(loop_count);
        }
    }

    private void freeUpSlot(int slot_number) {
        List<Vehicle> vehicleList = new ArrayList<>(bookKeeper.getVehicle_list());
        if(vehicleList == null || vehicleList.isEmpty() || vehicleList.stream().findFirst().filter(veh->veh.getSlot() == slot_number) == null) {
            System.out.println("Slot is already free!");
        } else {
            vehicleList.removeAll(vehicleList.stream().filter(veh->veh.getSlot() == slot_number).collect(Collectors.toList()));
            System.out.println("Slot number "+slot_number+" is free");
            bookKeeper.setVehicle_list(vehicleList);
            availableSlots.add(slot_number);
        }
    }

    private void printStatus() {
        System.out.println("Slot No.  Registration No    Colour");
        for (Vehicle vehicle:
             bookKeeper.getVehicle_list()) {
            System.out.println(vehicle.getSlot() + "         " + vehicle.getRegistration_number() + "          " + vehicle.getColor());
        }
    }
}
