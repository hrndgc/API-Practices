package com.techproed.gets;

import com.techproed.testBase.TestBaseRestApiExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends TestBaseRestApiExample {
    /*
   http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde,
    status kodun 200,
    gelen body de,
    5. çalışanın isminin "Airi Satou" olduğunu ,
    6. çalışanın maaşının "372000" olduğunu ,
    Toplam 24 tane çalışan olduğunu,
    "Rhona Davidson" ın employee lerden biri olduğunu
    "21", "23", "61" yaşlarında employeeler olduğunu test edin
     */

    @Test
    public void test01(){

        spec03.pathParam("param1", "employees");

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{param1}");


        response.prettyPrint();

        //response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.getStatusCode());

        JsonPath json=response.jsonPath();
        Assert.assertEquals("Airi Satou", json.getString("data.employee_name[4]"));
        Assert.assertEquals(372000,json.getInt("data.employee_salary[5]"));
        Assert.assertEquals(24,json.getList("data.id").size());
        Assert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));
       // Assert.assertTrue(json.getList("data.employee_age").contains(21));
       // Assert.assertTrue(json.getList("data.employee_age").contains(23));
       // Assert.assertTrue(json.getList("data.employee_age").contains(61));

        List<Integer> ageList = new ArrayList<Integer>();
        ageList.add(21);
        ageList.add(23);
        ageList.add(61);

        Assert.assertTrue(json.getList("data.employee_age").containsAll(ageList));

    }
}
