package com.techproed.days;

import com.techproed.testBase.TestBaseRestApiExample;
import com.techproed.testDatas.TestDataRestApiExample;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest13WithMatchers extends TestBaseRestApiExample {

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

        response.then().
                assertThat().
                statusCode(200).
                body("data[4].employee_name", equalTo(expectedData.get("employee_name5")),
                        "data.id", hasSize((Integer) expectedData.get("employeeCount")),
                        "data[-2].employee_salary", equalTo(expectedData.get("salarylast2")),
                        "data.employee_age", hasItems(
                                ((List)expectedData.get("ages")).get(0),
                                ((List)expectedData.get("ages")).get(1),
                                ((List)expectedData.get("ages")).get(2)),
                        "data[10].employee_name", equalTo(((Map)expectedData.get("employee11")).get("employee_name")),
                        "data[10].employee_salary", equalTo(((Map)expectedData.get("employee11")).get("employee_salary")),
                        "data[10].employee_age", equalTo(((Map)expectedData.get("employee11")).get("employee_age")),
                        "data[10].profile_image", equalTo(((Map)expectedData.get("employee11")).get("profile_image")),
                        "data[10].id", equalTo(((Map)expectedData.get("employee11")).get("id"))
                     );


    }
}
