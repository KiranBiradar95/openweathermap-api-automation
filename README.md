# openweathermap-api-automation

This module will give you the response of the Current Weather details based on "City_Name", " City ID" or "Code" and API Key.

Generate the API Key from https://home.openweathermap.org/api_keys

### Request:

  URI: https://api.openweathermap.org
  
  EndPoint:/data/2.5/weather
  
  QueryParams: q=city name
               appid=API key 

  https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

  https://api.openweathermap.org/data/2.5/weather?q={city name},{state code}&appid={API key}

  https://api.openweathermap.org/data/2.5/weather?q={city name},{state code},{country code}&appid={API key}

### Sample Request

```
curl --location --request GET 'api.openweathermap.org/data/2.5/weather?q=Boston&appid=123456789abcd'
```

### Response:

```
{
    "coord": {
        "lon": -71.0598,
        "lat": 42.3584
    },
    "weather": [
        {
            "id": 804,
            "main": "Clouds",
            "description": "overcast clouds",
            "icon": "04n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 272.54,
        "feels_like": 270.26,
        "temp_min": 270.34,
        "temp_max": 274.31,
        "pressure": 1019,
        "humidity": 63
    },
    "visibility": 10000,
    "wind": {
        "speed": 1.79,
        "deg": 313,
        "gust": 3.58
    },
    "clouds": {
        "all": 97
    },
    "dt": 1642049926,
    "sys": {
        "type": 2,
        "id": 2013408,
        "country": "US",
        "sunrise": 1641989515,
        "sunset": 1642023166
    },
    "timezone": -18000,
    "id": 4930956,
    "name": "Boston",
    "cod": 200
}
```
