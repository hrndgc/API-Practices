package com.techproed.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseJsonPlaceHolder {

    protected RequestSpecification spec01;

    @Before
    public void setup(){

        spec01 = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com").
                build();
    }
}
