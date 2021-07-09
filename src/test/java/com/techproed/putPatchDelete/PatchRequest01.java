package com.techproed.putPatchDelete;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import com.techproed.testDatas.TestDataJsonPlaceHolder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class PatchRequest01 extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {

      "title": "API calismaliyim"

     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}
     */

    @Test
    public void test01(){

        spec01.pathParams("param1", "todos", "param2", 198);

        //requestbody
        TestDataJsonPlaceHolder testData=new TestDataJsonPlaceHolder();
        JSONObject requestBody=testData.setUpPatch01();

        //expected
        JSONObject expectedData = testData.setUpExpectedPatch01();
        System.out.println("expected:" + expectedData);


        Response response= RestAssured.given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(requestBody.toString()).
                when().
                patch("/{param1}/{param2}");
        System.out.println("response: ");
        response.prettyPrint();

        //De-Seriallization
        HashMap<String ,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getBoolean("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedData.getInt("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedData.getString("title"), actualDataMap.get("title"));



    }

}
