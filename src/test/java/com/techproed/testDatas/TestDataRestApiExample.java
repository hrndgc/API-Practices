package com.techproed.testDatas;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataRestApiExample {
    /*
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
     }
             */

    public HashMap<String, Object> setUpTestData(){



        List<Integer> ages = new ArrayList<Integer>();
        ages.add(40);
        ages.add(21);
        ages.add(19);

        HashMap<String,Object> employee11 = new HashMap<String, Object>();
        employee11.put("id",11);
        employee11.put("employee_name","Jena Gaines");
        employee11.put("employee_salary",90560);
        employee11.put("employee_age",30);
        employee11.put("profile_image","");


        HashMap<String,Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("statusCode" , 200);
        expectedDataMap.put("employee_name5", "Airi Satou");
        expectedDataMap.put("employeeCount", 24);
        expectedDataMap.put("salarylast2", 106450);
        expectedDataMap.put("ages" , ages);
        expectedDataMap.put("employee11",employee11);



        return expectedDataMap;
    }


    public HashMap<String, Integer> setUpTestData2(){
            /*
            Status kodun 200 olduğunu,
            En yüksek maaşın 725000 olduğunu,
            En küçük yaşın 19 olduğunu,
            İkinci en yüksek maaşın 675000
             */
            HashMap<String, Integer> expectedData2 = new HashMap<String, Integer>();
            expectedData2.put("statusCode", 200);
            expectedData2.put("maxSalary", 725000);
            expectedData2.put("minAge", 19);
            expectedData2.put("maxSalary2", 675000);

            return expectedData2;
    }

    public HashMap<String, Object> setUpTestData3(){
        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
        requestBodyMap.put("name", "Ahmet Aksoy");
        requestBodyMap.put("salary", 1000);
        requestBodyMap.put("age", 18);
        requestBodyMap.put("profile_image" , "");

        return requestBodyMap;
    }


    public HashMap<String, Object>  setUpTestData4(){

        HashMap<String, Object> expectedData4 = new HashMap<String,Object>();
        expectedData4.put("statusCode", 200);
        expectedData4.put("status", "success");
        expectedData4.put("message", "Successfully! Record has been added.");

        return expectedData4;
    }

    public JSONObject setUpDelete01(){
        JSONObject expectedData = new JSONObject();

        expectedData.put("status", "success");
        expectedData.put("data","2");
        expectedData.put("message","Successfully! Record has been deleted");
        expectedData.put("statusCode", 200);

        return expectedData;
    }

}
