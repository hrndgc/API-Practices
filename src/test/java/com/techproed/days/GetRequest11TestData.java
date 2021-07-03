package com.techproed.days;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import com.techproed.testDatas.TestDataJsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest11TestData extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
    Dönen response un Status kodunun 200, dönen body de,
       "completed": değerinin false
       "title”: değerinin “quis ut nam facilis et officia qui”
       "userId"  sinin 1 ve header değerlerinden
        "Via" değerinin “1.1 vegur” ve
       "Server" değerinin “cloudflare” olduğunu test edin…
     */

    @Test

    public void test01(){
        //1-url oluştur
        spec01.pathParams(
                "param1", "todos",
                "param2", 2);


        //2- expected data oluştur
        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        HashMap<String, Object> expectedDataMap = testDataJsonPlaceHolder.setUpTestData();


        //3-Request gönder
        Response response = given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{param1}/{param2}");

        response.prettyPrint();

        //4- Actual Datayı olustur
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"), actualDataMap.get("id"));
        Assert.assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("Via"), response.getHeader("Via"));
        Assert.assertEquals(expectedDataMap.get("Server"), response.getHeader("Server"));

    }

}

