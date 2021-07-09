package com.techproed.posts;

import com.techproed.testBase.TestBaseHerokuApp;
import com.techproed.testDatas.TestDataHerokuApp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends TestBaseHerokuApp {

    /*
    https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
{ "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
 "booking": {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
     */
    @Test
    public void test01(){

        //url oluştur....
        spec02.pathParam("param1", "booking");

        //requestBody i oluştur....
        TestDataHerokuApp testData = new TestDataHerokuApp();
        JSONObject requestBodyJSONObject = testData.setUpTestData2();

        Response response= given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().basic("admin", "password123").
                body(requestBodyJSONObject.toString()).when().post("/{param1}");

        response.prettyPrint();

        //Bu örnekte response olarak benim gönderdiğim  Body değerleri değerleri döndüğü için
        // Expected Data olarak gönderdiğim değerleri kullanabilirim


        HashMap<String ,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(requestBodyJSONObject.getString("firstname"),
                ((Map)actualDataMap.get("booking")).get("firstname"));

        Assert.assertEquals(requestBodyJSONObject.getString("lastname"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("lastname"));

        Assert.assertEquals(requestBodyJSONObject.getInt("totalprice"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("totalprice"));

        Assert.assertEquals(requestBodyJSONObject.getBoolean("depositpaid"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("depositpaid"));

        Assert.assertEquals(requestBodyJSONObject.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));

        Assert.assertEquals(requestBodyJSONObject.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));


    }
}
