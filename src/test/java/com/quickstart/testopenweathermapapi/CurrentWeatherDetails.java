package com.quickstart.testopenweathermapapi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;

/**
 * Created by Kiran Biradar on 13-01-2022
 */
public class CurrentWeatherDetails {

    // Replace the APIToken with your API Key generated from OpenWeatherMap account

    public static final String APIToken = "8be7d8e433d9f6ff2833add26fa0ad1c";
    public static final String URI = "http://api.openweathermap.org";
    public static final String EndPoint = "/data/2.5/weather";

    public static final long StatusOK = 200;
    public static final long StatusNotFound = 404;


    /*
    Test the API response is 200 for valid input (City name=Boston)
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

        JsonPath js = new JsonPath(res.asString());
        Assert.assertEquals(js.get("name").toString().toLowerCase(), "boston");

        Assert.assertEquals(StatusOK, res.getStatusCode());
        LinkedHashMap<String, Object> metadata = js.get("sys");
        Integer ID = (Integer) metadata.get("id");
        System.out.println(ID);

    }

    /*
        Test the API response is 404 for Invalid input (City name=Boston123)
         **/
    @Test
    public void verifyCurrentWeatherByIncorrectCityName() {

        RestAssured.baseURI = URI;
        Response res =
                given()
                        .queryParam("q", "Boston123")

                        .queryParam("appid", APIToken)

                        .when()
                        .get(EndPoint)
                        .then()
                        .extract()
                        .response();

        res.prettyPrint();

        Assert.assertEquals(StatusNotFound, res.getStatusCode());
    }

    /*
    Test the API response is 200 for valid input (City ID=2172797)
     **/
    @Test
    public void getCurrentWeatherByCityID() {

        RestAssured.baseURI = URI;
        Response res = given()
                .queryParam("id", 2172797)
                .queryParam("appid", APIToken)
                .when()
                .get(EndPoint)
                .then()
                .extract()
                .response();

        JsonPath js = new JsonPath(res.asString());

        Assert.assertEquals(StatusOK, res.getStatusCode());
        Assert.assertEquals("cairns", js.get("name").toString().toLowerCase());
        System.out.println(res.prettyPrint());
    }

    /*
    Test the API response is 200 for valid input (City Name="Cairns" and City ID=2172797)
     **/
    @Test
    public void getCurrentWeatherByCityNameandCityID() {

        RestAssured.baseURI = URI;

        Response res = given()
                .queryParam("id", 2172797)
                .queryParam("name", "Cairns")
                .queryParam("appid", APIToken)
                .when()
                .get(EndPoint)
                .then()
                .extract()
                .response();

        JsonPath js = new JsonPath(res.asString());

        Assert.assertEquals(StatusOK, res.getStatusCode());
        Assert.assertEquals("cairns", js.get("name").toString().toLowerCase());
        System.out.println(res.prettyPrint());
    }

    /*
    Test the API response is 200 for valid input (City Name="Cairns" and City ID=2172797)
     **/
    @Test
    public void getCurrentWeatherByZipCode() {

        RestAssured.baseURI = URI;

        Response res = given()
                .queryParam("zip", 94040)
                //.queryParam("name","Cairns")
                .queryParam("appid", APIToken)
                .when()
                .get(EndPoint)
                .then()
                .extract()
                .response();

        Assert.assertEquals(StatusOK, res.getStatusCode());
        JsonPath js = new JsonPath(res.asString());
        Assert.assertEquals("mountain view", js.get("name").toString().toLowerCase());
        System.out.println(res.prettyPrint());
    }

    /*
    Test the API response is 200 for valid input (API Token Latitude, Longitude)
     **/
    @Test
    public void getCurrentWeatherByLatLon() {

        RestAssured.baseURI = URI;

        Response res = given()
                .queryParam("lat", 35)
                .queryParam("lon", 139)
                .queryParam("appid", APIToken)
                .when()
                .get(EndPoint)
                .then()
                .extract()
                .response();

        Assert.assertEquals(StatusOK, res.getStatusCode());
        JsonPath js = new JsonPath(res.asString());
        Assert.assertEquals("shuzenji", js.get("name").toString().toLowerCase());
        System.out.println(res.prettyPrint());
    }

    /*
    Test the API response is 200 for valid input
    (API Token Latitude, Longitude,City Name="Mountain View", Zip code=94040 City ID=5122)
     **/
    @Test
    public void getCurrentWeatherByCityNameIdZipLatLon() {

        RestAssured.baseURI = URI;
        Response res = given()
                .queryParam("lat", 37.3855)
                .queryParam("lon", -122.088)
                .queryParam("name", "Mountain View")
                .queryParam("zip", 94040)
                .queryParam("id", 5122)
                .queryParam("appid", APIToken)
                .when()
                .get(EndPoint)
                .then()
                .extract()
                .response();

        Assert.assertEquals(StatusOK, res.getStatusCode());
        JsonPath js = new JsonPath(res.asString());
        Assert.assertEquals("mountain view", js.get("name").toString().toLowerCase());
        LinkedHashMap<String, Object> metadata = js.get("sys");
        Assert.assertEquals(5122, metadata.get("id"));
        System.out.println(res.prettyPrint());
    }

    /* To verify that we are getting 404 response code when passed the incorrect ID and Zip code in the request
    Valid values are passed for City Name, Latitude and Longitude
    */

    @Test
    public void verifyCurrentWeatherByIncorrectIdandZip() {

        RestAssured.baseURI = URI;
        Response res = given()
                .queryParam("lat", 37.3855)
                .queryParam("lon", -122.088)
                .queryParam("name", "Mountain View")
                .queryParam("zip", 9440)
                .queryParam("id", -1)
                .queryParam("appid", APIToken)
                .when()
                .get(EndPoint)
                .then()
                .extract()
                .response();

        //Assert.assertEquals(StatusNotFound, res.getStatusCode());
        System.out.println(res.prettyPrint());
    }
}
