package com.techproed.gets;

import com.techproed.testBase.TestBaseRestApiExample;
import com.techproed.testDatas.TestDataRestApiExample;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends TestBaseRestApiExample {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,
    çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
     {
         “id”:”11”
        "employee_name": "Jena Gaines",
        "employee_salary": "90560",
        "employee_age": "30",
        "profile_image": "" }
      } gibi olduğunu test edin.
     */

    @Test
    public void test01(){
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

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        Assert.assertEquals(expectedData.get("statusCode"), response.getStatusCode());

        Assert.assertEquals(expectedData.get("employee_name5"),
                ((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name"));

        Assert.assertEquals(expectedData.get("employeeCount"),
                ((List)actualDataMap.get("data")).size());

        int dataSize = ((List)actualDataMap.get("data")).size();
        Assert.assertEquals(expectedData.get("salarylast2"),
                ((Map)((List)actualDataMap.get("data")).get(dataSize-2)).get("employee_salary"));


    /*
        List<Integer> ageList = (List<Integer>) ((List) actualDataMap.get("data")).
                        stream().map(x -> ((Map) x).get("employee_age")).
                        collect(Collectors.toList());
            Assert.assertTrue(ageList.containsAll((List) expectedData.get("ages")));

     */

        List<Integer> ageList = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            int age = (int)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age");
            ageList.add(age);
        }
        Assert.assertTrue(ageList.containsAll((List) expectedData.get("ages")));


      Assert.assertEquals(expectedData.get("employee11"), ((List)actualDataMap.get("data")).get(10));

    }

}

