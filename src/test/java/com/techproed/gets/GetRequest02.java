package com.techproed.gets;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class GetRequest02 {
    /*https://restful-booker.herokuapp.com/booking url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    content type'inin "application/json" oldugunu test edin

    https://restful-booker.herokuapp.com/booking/1001 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 404 oldugunu*/
    // 1- given() pre-conditonları yazdıgımız yer
    // 2 - when().get(url)
    // 3 - then().

    @Test
    public void test01(){
        // 1- url path belirle
        String url = "https://restful-booker.herokuapp.com/booking";
        // 2 - expected ları belirleme
        // 3 - request gonder
        Response response = given().accept(ContentType.JSON).
                when().get(url);
        //response.prettyPrint();
        //System.out.println(response.getBody().asString());
        // 4 - response ile assertları yap
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        System.out.println(response.getBody().asString());

        System.out.println("status code: "+response.getStatusCode());
        System.out.println("======================");
        System.out.println("status line: " + response.getStatusLine());
        System.out.println("======================");
        System.out.println(response.getHeaders());
        System.out.println("======================");
        System.out.println("content type: " + response.getContentType());
        System.out.println("======================");
        System.out.println(response.getHeader("Server"));
        System.out.println("======================");
        System.out.println("date: "+ response.getHeader("Date"));
    }


    @Test
    public void test02(){
         /*
           https://restful-booker.herokuapp.com/booking/1001 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 404 oldugunu
    ve Response body'sinin "Not Found" icerdigini
    ve Response body'sinin "API" icermedigini test edin
         */
            String url="https://restful-booker.herokuapp.com/booking/1001";
            Response response=given().
                    accept("application/json").
                    when().
                    get(url);

            response.prettyPrint();

            response.then().assertThat().statusCode(404);
            Assert.assertTrue(response.asString().contains("Not Found"));
            Assert.assertFalse(response.asString().contains("API"));


    }
}
