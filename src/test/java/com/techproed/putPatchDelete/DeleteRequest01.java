package com.techproed.putPatchDelete;

import com.techproed.testBase.TestBaseRestApiExample;
import com.techproed.testDatas.TestDataRestApiExample;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends TestBaseRestApiExample {
    /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}
     */

    @Test
    public void test01(){
        //url
        spec03.pathParams("param1", "delete", "param2", 2);

        //expected
        TestDataRestApiExample testData = new TestDataRestApiExample();
        JSONObject expectedData = testData.setUpDelete01();
        System.out.println(expectedData);

        //request
        Response response = RestAssured.given().
                spec(spec03).
                auth().basic("admin", "password123").
                when().
                delete("/{param1}/{param2}");
        response.prettyPrint();

        response.then().assertThat().
                statusCode(expectedData.getInt("statusCode")).
                body("status", equalTo(expectedData.getString("status")),
                        "data", equalTo(expectedData.getString("data")),
                        "message", equalTo(expectedData.getString("message")));


    }
}
