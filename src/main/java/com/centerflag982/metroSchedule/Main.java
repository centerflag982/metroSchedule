package com.centerflag982.metroSchedule;

import com.centerflag982.metroSchedule.dao.JDBCDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component ("MainInstance")
public class Main {

    //@Autowired
    //private UserInteraction uiInstance;
    private UserInteraction uiInstance = new UserInteraction();

    //@Autowired
    //private MetroDao daoInstance;
    private MetroDao daoInstance = new JDBCDao();

    public static void main (String [] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        Main mainInst = (Main) context.getBean("MainInstance");
        mainInst.run();
    }

    private void run(){
        List<Stop> stationList = daoInstance.getStationList();
        uiInstance.listStations(stationList);
        String userStationName = uiInstance.getUserStation(stationList);
        List<String> arrivalTimes = daoInstance.getArrivalTimes(userStationName);
        uiInstance.getNextArrival(arrivalTimes);
    }

}
