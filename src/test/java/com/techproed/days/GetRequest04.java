package com.techproed.days;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest04 {
    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in “Jim"
    ve totalprice’in 600
    ve checkin date'in 2015-06-12"oldugunu test edin

    Not: Veriler anlık olarak değişebiliyor.
     */

    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking/5";

        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.then().
                assertThat().statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("Susan"),
                        "totalprice", equalTo(554),
                        "bookingdates.checkin", equalTo("2020-12-11"));


    }
}
