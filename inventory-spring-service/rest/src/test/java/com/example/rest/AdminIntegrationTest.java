package com.example.rest;


import com.example.rest.login.model.User;
import com.example.rest.login.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { RestApplication.class, TestIntegrationConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${local.server.port}")
    int port;
    private FormAuthConfig formConfig;

    @BeforeEach
    public void init() {
        User user = userRepository.findUser("admin");
        if (user == null) {
            user = new User("admin","password","ADMIN");

            userRepository.addUser(user);
        } else {
            user.setPassword(passwordEncoder.encode("password"));
            userRepository.addUser(user);
        }

        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";

        formConfig = new FormAuthConfig("/login", "username", "password");
    }

    @AfterEach
    public void cleanup() {

    }

    @Test
    public void login_logout() {

        final RequestSpecification request = RestAssured.given().auth().form("admin", "password", formConfig);

        final Map<String, String> params = new HashMap<>();

        final Response response = request.with().params(params).get("http://localhost:8080/homepage.html");

        assertEquals(200, response.statusCode());

        request.get("http://localhost:8080/logout");
    }

    @Test
    public void login_access_buyers_listInventory() {

        final RequestSpecification request = RestAssured.given().auth().form("admin", "password", formConfig);

        final Map<String, String> params = new HashMap<>();


        final Response response = request.with().params(params).get("http://localhost:8080/api/v1/buyerActions/listInventory/");

        assertEquals(403, response.statusCode());

        request.get("http://localhost:8080/logout");

    }

    @Test
    public void login_access_test_admin_inventory_url() {

        final RequestSpecification request = RestAssured.given().auth().form("admin", "password", formConfig);

        final Map<String, String> params = new HashMap<>();

        final Response response = request.with().params(params).get("http://localhost:8080/api/v1/adminActions/listInventory/");

        assertEquals(200, response.statusCode());

        request.get("http://localhost:8080/logout");
    }

    @Test
    public void add_car_test() {

        final RequestSpecification request = RestAssured.given().auth().form("admin", "password", formConfig);

        final Map<String, String> params = new HashMap<>();

        final  Header  header = new Header("Content-Type", "application/json");

        final Response response = request.with().body("{ \"make\": \"Toyota\", \"model\": \"lexas\", \"year\": \"2020\" }").header(header)
                .post("http://localhost:8080/api/v1/adminActions/addCar/");

        assertEquals(200, response.statusCode());

        final Response response2 = request.with().params(params)
                .get("http://localhost:8080/api/v1/adminActions/listCars/");
        assertEquals(200, response2.statusCode());
        //System.out.println(response2.body().asString());
        assertTrue(response2.body().asString().contains("Toyota"));

        request.get("http://localhost:8080/logout");
    }

}
