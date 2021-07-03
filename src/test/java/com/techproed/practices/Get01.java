package com.techproed.practices;

import com.techproed.testBase.TestBaseHerokuApp;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get01 extends TestBaseHerokuApp {
    /*
         Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://restful-booker.herokuapp.com/booking/10
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Mark"
     And lastname "Wilson"
     And depositpaid true
     And checkin date "2016-06-19"
     And checkout date "2019-08-26"
     1.Adim Url'i olustur
     2.Adim Data'yi oluştur
     3.Adim Request gönder
     4.Adim Validation yap
     */

    @Test
    public void test01() {

        spec02.pathParams("name", "booking",
                "id", 10);

        Response response = RestAssured.given().
                accept("application/json").
                spec(spec02).when().
                get("/{name}/{id}");

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("Mark"),
                        "lastname", equalTo("Jackson"),
                        "totalprice", equalTo(970),
                        "bookingdates.checkin", equalTo("2015-02-11"));

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals("Mark", jsonPath.getString("firstname"));
    }

}
