package com.techproed.posts;

import com.techproed.testBase.TestBaseRestApiExample;
import com.techproed.testDatas.TestDataRestApiExample;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends TestBaseRestApiExample {
    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ahmet Aksoy",
           "salary":"1000",
           "age":"18",
           "profile_image": ""
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
   "status": "success",
           "data": {
        “id”:…
   },
   "message": "Successfully! Record has been added."
}
olduğunu test edin
     */


    @Test
    public void test01(){

        spec03.pathParam("param1", "create");

        TestDataRestApiExample testData = new TestDataRestApiExample();
        HashMap<String, Object> requestBodyMap = testData.setUpTestData3();
        System.out.println(requestBodyMap);

        HashMap<String, Object> expectedDataMap = testData.setUpTestData4();
        System.out.println(expectedDataMap);


        Response response = given().
                accept("application/json").
                spec(spec03).
                body(requestBodyMap).
                auth().basic("admin", "password123").
                when().
                post("/{param1}");

        response.prettyPrint();

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));

    }


}
