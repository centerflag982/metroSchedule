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
        String userStationName = stationList.get(0).getStopName();
        scanner.close();
        return userStationName;
    }

    public void getNextArrival(List<String> arrivalTimes){
        LocalTime currentTime = LocalTime.now();

        for (String arrTime : arrivalTimes){
            int arrivalHour = Integer.parseInt(arrTime.substring(0,2));
            int arrivalMinute = Integer.parseInt(arrTime.substring(3,5));
            if (arrivalHour >= currentTime.getHour()){
                int hoursTillArrival = arrivalHour - currentTime.getHour();
                int minutesTillArrival = arrivalMinute - currentTime.getMinute() + (hoursTillArrival * 60);
                outputInstance.displayOutput(minutesTillArrival + " minutes until next train arrives.");
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
