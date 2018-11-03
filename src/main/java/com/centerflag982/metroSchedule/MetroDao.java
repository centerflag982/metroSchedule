package com.centerflag982.metroSchedule;

import java.util.List;

public interface MetroDao {
    List<Stop> getStationList();
    List<String> getArrivalTimes(String stationName);
}
