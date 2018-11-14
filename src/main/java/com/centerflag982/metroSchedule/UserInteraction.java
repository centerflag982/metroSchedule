package com.centerflag982.metroSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

@Component("uiInstance")
public class UserInteraction {

    //@Autowired
    //private AppOutput outputInstance;
    private AppOutput outputInstance = new ConsoleOutput();

    void listStations(List<Stop> stationList){
        outputInstance.displayOutput("Listing all MetroLink stations:");
        int tempStationID = 1;
        for (Stop station : stationList){
            System.out.println(tempStationID + ": " + station.getStopName());
            tempStationID++;
        }
    }

    String getUserStation(List<Stop> stationList){
        Scanner scanner = new Scanner(System.in);
        outputInstance.displayOutput("Please enter the number of your current station:");
        int userStationIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Stop userStation = stationList.get(userStationIndex);
        String userStationName = userStation.getStopName();
        scanner.close();
        return userStationName;
    }

    public void getNextArrival(List<String> arrivalTimes){
        LocalTime currentTime = LocalTime.now();

        for (String arrTime : arrivalTimes){
            int arrivalAbsoluteMinutes = (Integer.parseInt(arrTime.substring(0,2)) * 60) + Integer.parseInt(arrTime.substring(3,5)); //converts time into minutes since beginning of day
            int currentAbsoluteMinutes = (currentTime.getHour() * 60) + currentTime.getMinute(); //converts time into minutes since beginning of day
            if (arrivalAbsoluteMinutes >= currentAbsoluteMinutes){
                int minutesTillArrival = arrivalAbsoluteMinutes - currentAbsoluteMinutes;
                outputInstance.displayOutput(minutesTillArrival + " minutes until next train arrives (" + arrTime + ").");
                //outputInstance.displayOutput(hoursTillArrival + " hours and " + minutesTillArrival + " minutes until next train arrives.");
                break;
            }
        }

        /*for (String arrTime : arrivalTimes){
            LocalTime arrTimeLT = LocalTime.parse(arrTime);
            if (arrTimeLT.isAfter(currentTime)){
                System.out.println(currentTime.until(arrTimeLT, ChronoUnit.MINUTES) + " minutes until next train arrives.");
                break;
            }
        }*/
    }
}
