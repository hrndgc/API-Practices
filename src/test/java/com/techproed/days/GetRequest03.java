package com.techproed.days;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest03 {
    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in "Mark"
ve lastname'in "Jackson"
ve checkin date'in 2019-09-20"
ve checkout date'in 2020-07-13 oldugunu test edin

Not: Veriler anlık olarak değişebiliyor.
     */

    @Test
    public void test03(){
        String url="https://restful-booker.herokuapp.com/booking/7";
        Response response=given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        /*
       response.then().
               assertThat().
               statusCode(200).
               contentType(ContentType.JSON).
               body("firstname", Matchers.equalTo("Mark")).
               body("lastname",Matchers.equalTo("Jackson")).
               body("totalprice",Matchers.equalTo("993")).
               body("bookingdates.checkin",Matchers.equalTo("2019-09-20")).
               body("bookingdates.checkout",Matchers.equalTo("2020-07-13"));

       */
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Mark"),
                        "lastname", equalTo("Jones"),
                        "bookingdates.checkin", equalTo("2017-09-13"),
                        "bookingdates.checkout", equalTo("2020-01-13") );

        //System.out.println(response.getBody().asString());
        // Assert.assertTrue(response.asString().contains("Not Found"));
        //Assert.assertFalse(response.asString().contains("API"));
    }

}
