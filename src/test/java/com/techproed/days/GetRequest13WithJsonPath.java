package com.techproed.days;

import com.techproed.testBase.TestBaseRestApiExample;
import com.techproed.testDatas.TestDataRestApiExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest13WithJsonPath extends TestBaseRestApiExample {
    /*
        http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
  {
 "id":"11"
 "employee_name": "Jena Gaines",
"employee_salary": "90560",
"employee_age": "30",
"profile_image": "" }
} gibi olduğunu test edin.
     */

    @Test
    public void test01() {
        spec03.pathParam(
                "param1", "employees");

        TestDataRestApiExample testDataRestApiExample = new TestDataRestApiExample();
        HashMap<String, Object> expectedData = testDataRestApiExample.setUpTestData();

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{param1}");

        response.prettyPrint();

        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedData.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedData.get("employee_name5"), json.getString("data[4].employee_name"));
        Assert.assertEquals(expectedData.get("employeeCount"),json.getList("data.id").size());
        Assert.assertEquals(expectedData.get("salarylast2"), json.getInt("data[-2].employee_salary"));
        Assert.assertTrue(json.getList("data.employee_age").containsAll((List) expectedData.get("ages")));
        Assert.assertEquals(expectedData.get("employee11"), json.getMap("data[10]"));
    }
}
