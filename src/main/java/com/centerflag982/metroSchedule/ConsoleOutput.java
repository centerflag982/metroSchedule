package com.centerflag982.metroSchedule;

import org.springframework.stereotype.Component;

@Component("outputInstance")
public class ConsoleOutput implements AppOutput {
    public void displayOutput(String outputString){
        System.out.println(outputString);
    }
}
