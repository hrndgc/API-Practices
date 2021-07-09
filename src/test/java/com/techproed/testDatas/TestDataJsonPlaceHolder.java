package com.techproed.testDatas;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataJsonPlaceHolder {

    public int statusCode = 201;

    public HashMap<String, Object> setUpTestData(){

        HashMap<String, Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("statusCode" , 200);
        expectedDataMap.put("userId", 1);
        expectedDataMap.put("id", 2);
        expectedDataMap.put("title", "quis ut nam facilis et officia qui");
        expectedDataMap.put("completed", false);
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        return expectedDataMap;
    }

    public JSONObject setUpTestData2(){

        JSONObject todos=new JSONObject();
        todos.put("userId",55);
        todos.put("title","Tidy your room");
        todos.put("completed",false);

        return todos;
    }

    public JSONObject setUpPut01(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", 21);
        requestBody.put("title", "Wash the dishes");
        requestBody.put("completed", false);

        return requestBody;

    }

    public JSONObject setUpPatch01(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "API calismaliyim");

        return requestBody;
    }


    public JSONObject setUpExpectedPatch01(){
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("userId", 10);
        expectedBody.put("title", "API calismaliyim");
        expectedBody.put("completed", true);

        return expectedBody;
    }

}
