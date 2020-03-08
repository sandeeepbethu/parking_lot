package com.gojek.assessment.service;

import com.gojek.assessment.common.Operations;
import com.gojek.assessment.model.LineCommand;
import com.gojek.assessment.model.Vehicle;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class CoreService {

    private final CommandInterpreter commandInterpreter;

    private List<String> operationsList = Arrays.stream(Operations.values())
            .map(Enum::name)
            .collect(Collectors.toList());

    private LineCommand lineCommand;

    public CoreService(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public String loadInput() throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter file name: ");
            String file_name = scanner.next();
            fileReader = new FileReader(file_name);
            bufferedReader = new BufferedReader(fileReader);
            String currentLine;
            LineCommand lineCommand;
            List<LineCommand> lineCommandList = new ArrayList<>();

            while ((currentLine = bufferedReader.readLine()) != null) {
                if((lineCommand = processInputFile(currentLine)) != null)
                    lineCommandList.add(lineCommand);
            }

            if(lineCommandList.size() > 0) {
                commandInterpreter.executeFileCommands(lineCommandList);
                return "success";
            }
            else {
                System.out.println("Empty file/ unable to process. Check your input.");
                return "Blank input file";
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "fail";
        } finally {
            if(bufferedReader != null)
                bufferedReader.close();
            if(fileReader != null)
                fileReader.close();
        }
    }

    private LineCommand processInputFile(String line) {
        String trimLine;
        String[] lineArray;
        Vehicle vehicle;

        if(line!=null && (trimLine = line.trim()) != "") {
            lineArray = trimLine.split("\\s+");
            if(lineArray.length > 0 && operationsList.contains(lineArray[0].toUpperCase())) {
                lineCommand = new LineCommand();
                lineCommand.setCommand(lineArray[0].toUpperCase());

                if(lineArray.length == 2)
                    lineCommand.setDynamic_input(lineArray[1]);
                else if(lineArray.length == 3) {
                    vehicle = new Vehicle();
                    vehicle.setRegistration_number(lineArray[1]);
                    vehicle.setColor(lineArray[2]);
                    lineCommand.setVehicle(vehicle);
                } else if(lineArray.length != 1) {
                    System.out.println("Unable to process :: " + line);
                    return null;
                }

                return lineCommand;
            } else {
                System.out.println("Unable to process :: " + line);
                return null;
            }
        }

        return null;
    }
}
