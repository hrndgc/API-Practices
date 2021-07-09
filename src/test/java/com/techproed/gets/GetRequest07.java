package com.techproed.gets;

import com.techproed.testBase.TestBaseHerokuApp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest07 extends TestBaseHerokuApp {

    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine bir request yolladigimda
     	HTTP Status Code'unun 200
     	ve response content type'inin "application/JSON" oldugunu
			ve response body'sinin asagidaki gibi oldugunu test edin
		{
           "firstname": "Jim",
             "lastname": "Jones",
             "totalprice": 898,
             "depositpaid": false,
             "bookingdates": {
                "checkin": "2019-09-07",
                 "checkout": "2020-12-24"
         }

         Not: Veriler anlık olarak değişebiliyor
     */

    @Test
    public void test01(){

        spec02.pathParams("name", "booking",
                            "id", 5);

        Response response = given().
                accept("application/json").
                spec(spec02).when().
                get("/{name}/{id}");

        response.prettyPrint();

        /*
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("Jim"),
                        "lastname", equalTo("Jones"),
                        "totalprice", equalTo(898),
                        "bookingdates.checkin", equalTo("2019-09-07"));

         */

        JsonPath json=response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Eric", json.getString("firstname"));
        Assert.assertEquals(203, json.getInt("totalprice"));
        Assert.assertEquals(true, json.getBoolean("depositpaid"));
        Assert.assertEquals("2021-06-29", json.getString("bookingdates.checkin"));

    }
}
