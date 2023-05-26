package com.example.demo;

import com.example.demo.domain.UserInfo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.with;

public class BankControllerTest {

    static {
        RestAssured.baseURI = "http://localhost:8082";
    }
    private RequestSpecification spec = with()
            .baseUri("http://localhost:8082")
            .basePath("/");

    @Test
    void bankControllerTest() {
        UserInfo[] UserInfos = spec.get("user/getAll")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(UserInfo[].class);

        Stream.of(UserInfos)
                .filter(userInfo -> userInfo.getUserName().equals("DIma"))
                .peek(userInfo -> System.out.println(userInfo.getUserName()))
                .map(userInfo -> userInfo.toString())
                .collect(Collectors.toList());
    }
}
