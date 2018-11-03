package com.centerflag982.metroSchedule.dao;

import com.centerflag982.metroSchedule.Stop;
import com.centerflag982.metroSchedule.MetroDao;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("daoInstance")
public class JDBCDao implements MetroDao {
    private static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:metrolink.db";
    private static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";

    private static final String SERVICE_ID_WEEKDAY = "1_merged_2460117";
    private static final String SERVICE_ID_SATURDAY = "2_merged_2460119";
    private static final String SERVICE_ID_SUNDAY = "3_merged_2460118";

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Cannot find database loader class", e);
        }
        return DriverManager.getConnection(JDBC_SQLITE_METROLINK_DB);
    }

    public List<Stop> getStationList(){

        try (Connection connection = getConnection()){
            PreparedStatement stationListQuery = connection.prepareStatement("SELECT * FROM stops WHERE stop_name LIKE '%METROLINK STATION%'");
            ResultSet stationListResults = stationListQuery.executeQuery();
            List<Stop> stationList = new ArrayList<>();
            while (stationListResults.next()){
                Stop stop = new Stop();
                stop.setStopID(stationListResults.getString("stop_id"));
                stop.setStopName(stationListResults.getString("stop_name"));
                stationList.add(stop);
            }
            return stationList;

        } catch (SQLException e){
            throw new RuntimeException("Error retrieving stop info", e);
        }
    }

    public List<String> getArrivalTimes (String stationName){

        String serviceString;
        LocalDate date = LocalDate.now();
        DayOfWeek day = date.getDayOfWeek();
        switch (day) {
            case SATURDAY:
                serviceString = SERVICE_ID_SATURDAY;
                break;
            case SUNDAY:
                serviceString = SERVICE_ID_SUNDAY;
                break;
            default:
                serviceString = SERVICE_ID_WEEKDAY;
        }

        try (Connection connection = getConnection()){
            PreparedStatement arrivalTimeQuery = connection.prepareStatement("SELECT arrival_time FROM metrolink_stops" +
                    "WHERE stop_name = " + stationName + " AND service_id = " + serviceString + " ORDER BY arrival_time ASC");
            ResultSet arrivalTimeResults = arrivalTimeQuery.executeQuery();
            List<String> arrivalTimes = new ArrayList<>();
            while (arrivalTimeResults.next()){
                arrivalTimes.add(arrivalTimeResults.getString("arrival_time"));
            }
            return arrivalTimes;

        } catch (SQLException e){
            throw new RuntimeException("Error retrieving stop info", e);
        }
    }
}
