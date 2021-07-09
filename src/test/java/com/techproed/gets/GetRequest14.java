package com.techproed.gets;

import com.techproed.testBase.TestBaseRestApiExample;
import com.techproed.testDatas.TestDataRestApiExample;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends TestBaseRestApiExample {


    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
olduğunu test edin.
     */
    @Test
    public void test01() {
        spec03.pathParam(
                "param1", "employees");

        TestDataRestApiExample testDataRestApiExample = new TestDataRestApiExample();
        HashMap<String, Integer> expectedData2 = testDataRestApiExample.setUpTestData2();

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{param1}");

        response.prettyPrint();

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        //Status kodun 200 olduğunu
        Assert.assertEquals(expectedData2.get("statusCode"),(Integer) response.getStatusCode());

        //En yüksek maaşın 725000 olduğunu
        int dataSize= ((List) actualDataMap.get("data")).size();
        /*
        List<Integer> salaryList = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            int salary = (int)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_salary");
            salaryList.add(salary);
        }
        Collections.sort(salaryList);
        Assert.assertEquals(expectedData2.get("maxSalary"),salaryList.get(salaryList.size()-1));

        */
        //En yüksek maaşın 725000 olduğunu
        List<Integer> salaryList1 = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            int salary = (int)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_salary");
            salaryList1.add(salary);
        }

        Integer maxMaas = salaryList1.stream().reduce(0, (x, y) -> x > y ? x : y);
        Assert.assertEquals(expectedData2.get("maxSalary"),maxMaas);


        //En küçük yaşın 19 olduğunu
        List<Integer> ageList = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            int age = (int)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age");
            ageList.add(age);
        }
        Collections.sort(ageList);
        Assert.assertEquals(expectedData2.get("minAge"),ageList.get(0));





    }
}
