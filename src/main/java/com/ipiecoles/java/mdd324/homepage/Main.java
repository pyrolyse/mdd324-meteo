package com.ipiecoles.java.mdd324.homepage;

import com.ipiecoles.java.mdd324.homepage.utils.Utils;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {


        String meteo = Utils.getPageContents("https://api.openweathermap.org/data/2.5/weather?q=Paris&appid=5dfc2a06c8157403e9107053a73aca92&lang=fr&main.temp=Celsius");

        //JSON
        Genson genson = new GensonBuilder().useRuntimeType(true).create();
        Map<String, Object> map = genson.deserialize(meteo, Map.class);

        String jsonOutput = genson.serialize(MeteoService.getMeteo(new Ville("Paris")));
        System.out.println(jsonOutput);

    }

}
