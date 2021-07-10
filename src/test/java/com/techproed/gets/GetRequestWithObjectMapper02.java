package com.techproed.gets;

import com.techproed.testBase.TestBaseHerokuApp;
import com.techproed.utilities.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GetRequestWithObjectMapper02 extends TestBaseHerokuApp {
    /*
    https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
status kodun 200 ve response body’nin
{
   "firstname": "Mark",
   "lastname": "Wilson",
   "totalprice": 284,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-08-10",
       "checkout": "2018-06-22"
   }
}
Not: Veriler anlık degisebiliyor
     */

    @Test
    public void test(){
        spec02.pathParams("param1", "booking", "param2" , 2);

        String jsonData = "{\n" +
                "   \"firstname\": \"Susan\",\n" +
                "   \"lastname\": \"Smith\",\n" +
                "   \"totalprice\": 297,\n" +
                "   \"depositpaid\": true,\n" +
                "   \"bookingdates\": {\n" +
                "       \"checkin\": \"2017-03-06\",\n" +
                "       \"checkout\": \"2021-06-12\"\n" +
                "   }\n" +
                "}";

        HashMap<String,Object> expectedDataMap = JsonUtil.convertJsonToJava(jsonData,HashMap.class);

        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                spec(spec02).
                when().
                get("/{param1}/{param2}");

        response.prettyPrint();

        HashMap<String,Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(),HashMap.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("checkin")).get("checkin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("checkout")).get("checkout"));
    }
}
