package com.techproed.testDatas;

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
}
