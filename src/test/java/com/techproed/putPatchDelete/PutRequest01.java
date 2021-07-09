package com.techproed.putPatchDelete;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import com.techproed.testDatas.TestDataJsonPlaceHolder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class PutRequest01 extends TestBaseJsonPlaceHolder {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
   {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
}
     */

    @Test
    public void test01(){

        spec01.pathParams("param1", "todos", "param2", 195);

        TestDataJsonPlaceHolder testData=new TestDataJsonPlaceHolder();
        JSONObject requestBody=testData.setUpPut01();

        Response response= RestAssured.given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(requestBody.toString()).
                when().
                put("/{param1}/{param2}");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(requestBody.getInt("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(requestBody.getInt("title"), jsonPath.getInt("title"));
        Assert.assertEquals(requestBody.getInt("completed"), jsonPath.getInt("completed"));


    }
}
