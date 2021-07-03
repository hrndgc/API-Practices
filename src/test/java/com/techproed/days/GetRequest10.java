package com.techproed.days;

import com.techproed.testBase.TestBaseRestApiExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends TestBaseRestApiExample {

    /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde
    Dönen response un Status kodunun 200,
     1)10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
     2)30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
     3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
     bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    @Test
    public void test01(){

        spec03.pathParam("param1", "employees");

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{param1}");

       // response.prettyPrint();

        Assert.assertEquals(200 , response.getStatusCode());

        JsonPath json = response.jsonPath();

        //Groovy dili: javanın alt dillerinden biridir.
        //it ifadesi this
        List<Integer> idList = json.getList("data.findAll{it.id>10}.id");
        System.out.println(idList);

        /*
        List<Integer> idList = json.getList("data.id");
        idList.stream().filter(x-> x>10).forEach(x-> System.out.print(x + " "));
        */

        Assert.assertEquals(14, idList.size());

        List<Integer> ageList = json.getList("data.findAll{it.employee_age<30}employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        Assert.assertEquals(Integer.valueOf("23"), ageList.get(ageList.size()-1));

        List<Integer> salaryList = json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(salaryList);
        Assert.assertTrue(salaryList.contains("Charde Marshall"));
    }
}
