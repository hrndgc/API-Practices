package com.techproed.posts;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import com.techproed.testDatas.TestDataJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …
    }
     */

    @Test
    public void test01(){

        spec01.pathParam("param1", "todos");


        TestDataJsonPlaceHolder testData = new TestDataJsonPlaceHolder();
        JSONObject requestBody = testData.setUpTestData2();

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(requestBody.toString()).when().post("/{param1}");

        //response.prettyPrint();

        //De-Serialization -- gson ile
        HashMap<String ,Object> actualDataMap=response.as(HashMap.class);
        //System.out.println(actualDataMap);

        Assert.assertEquals(testData.statusCode, response.statusCode());
        Assert.assertEquals(requestBody.getBoolean("completed"), actualDataMap.get("completed"));
        Assert.assertEquals(requestBody.getString("title"), actualDataMap.get("title"));
        Assert.assertEquals(requestBody.getInt("userId"), actualDataMap.get("userId"));

        //JsonPath ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals(requestBody.getBoolean("completed"), json.getBoolean("completed"));
        Assert.assertEquals(requestBody.getString("title"), json.getString("title"));
        Assert.assertEquals(requestBody.getInt("userId"), json.getInt("userId"));

        //Matchers ile
        response.then().assertThat().statusCode(testData.statusCode).
                body("completed", equalTo(requestBody.getBoolean("completed")),
                        "title",equalTo(requestBody.getString("title")),
                        "userId",equalTo(requestBody.getInt("userId")));



    }


}
