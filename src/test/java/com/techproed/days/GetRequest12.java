package com.techproed.days;

import com.techproed.testBase.TestBaseHerokuApp;
import com.techproed.testDatas.TestDataHerokuApp;
import com.techproed.testDatas.TestDataJsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends TestBaseHerokuApp {
    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
 dönen response body nin
  {
   "firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    }
} gibi olduğunu test edin.
     */

    @Test
    public void test01(){
        spec02.pathParams(
                "param1", "booking",
                "param2", 1);


        TestDataHerokuApp testBaseHerokuApp = new TestDataHerokuApp();
        HashMap<String, Object> expectedDataMap  = testBaseHerokuApp.setUpTestData();


        Response response = given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{param1}/{param2}");

        response.prettyPrint();

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        //System.out.println(actualDataMap);
        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));



    }
}
