package com.gojek.assessment.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Component
public class CoreService {
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
            while ((currentLine = bufferedReader.readLine()) != null){
                System.out.println(currentLine);
            }

            return "success";
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
}
