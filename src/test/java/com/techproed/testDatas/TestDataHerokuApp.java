package com.techproed.testDatas;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataHerokuApp {

    public HashMap<String, Object> setUpTestData(){

        HashMap<String, String> bookingdatesMap = new HashMap<String, String>();
        bookingdatesMap.put("checkin", "2020-11-04");
        bookingdatesMap.put("checkout", "2021-03-17");


        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("statusCode" , 200);
        dataMap.put("firstname", "Susan");
        dataMap.put("lastname", "Brown");
        dataMap.put("totalprice", 284);
        dataMap.put("depositpaid", true);
        dataMap.put("additionalneeds", "Breakfast");
        dataMap.put("bookingdates", bookingdatesMap);

        return dataMap;
    }

    public JSONObject setUpTestData2(){

        JSONObject bookingDates= new JSONObject();
        bookingDates.put("checkin","2020-09-09");
        bookingDates.put("checkout","2020-09-21");

        JSONObject booking=new JSONObject();
        booking.put("firstname","Selim");
        booking.put("lastname","Ak");
        booking.put("totalprice",11111);
        booking.put("depositpaid",true);
        booking.put("bookingdates",bookingDates);

        return booking;

    }
}
