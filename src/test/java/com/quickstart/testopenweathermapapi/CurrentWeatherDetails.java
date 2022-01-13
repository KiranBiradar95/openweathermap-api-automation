package com.quickstart.testopenweathermapapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Kiran Biradar on 13-01-2022
 */
public class CurrentWeatherDetails {

    // Replace the APIToken with your API Key generated from OpenWeatherMap account

    public static final String APIToken = "8be7d8e433d9f6ff2833add26fa0ad1c";
    public static final String URI = "http://api.openweathermap.org";
    public static final String EndPoint = "/data/2.5/weather";

    /*
    Test the API response is 200 for valid input (City name)
     **/
    @Test
    public void getCurrentWeatherByCityName() {
        RestAssured.baseURI = URI;

        Response res = given()
                .queryParam("q", "Boston")
                .queryParam("appid", APIToken)
                .when()
                .get(EndPoint)
                .then()
                .extract()
                .response();

        res.prettyPrint();

        Assert.assertEquals(200, res.getStatusCode());

    }

}
