package com.ipiecoles.java.mdd324.homepage;

import com.ipiecoles.java.mdd324.homepage.utils.Utils;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MeteoService {

    public static Meteo getMeteo(Ville city) throws IOException {
        String coucher;
        Long humidite;
        Long icon;
        String lever;
        Double temp;
        String temps;

        String meteo = Utils.getPageContents("https://api.openweathermap.org/data/2.5/weather?q=" + city.getCity() + "&appid=5dfc2a06c8157403e9107053a73aca92&lang=fr&main.temp=Celsius");

        //JSON
        Genson genson = new GensonBuilder().useRuntimeType(true).create();
        Map<String, Object> map = genson.deserialize(meteo, Map.class);


        HashMap<String, Object> sys = (HashMap<String, Object>) map.get("sys");
        Long sunriseInt = (Long) sys.get("sunrise");

        sunriseInt = sunriseInt * 1000;

        Date sunrise = new Date(sunriseInt);


        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        lever = df.format(sunrise);

        //------

        Long sunsetInt = (Long) sys.get("sunset");

        sunsetInt = sunsetInt * 1000;

        Date sunset = new Date(sunsetInt);

        coucher = df.format(sunset);

        System.out.println(coucher);

        //-----------------



        HashMap<String, Object> main = (HashMap<String, Object>) map.get("main");
        Long humidityint = (Long) main.get("humidity");

        humidite = humidityint;

        System.out.println(humidite);


        ArrayList icons = (ArrayList) map.get("weather");
        HashMap<String, Object> icones = (HashMap<String, Object>) icons.get(0);
        Long iconint = (Long) icones.get("id");

        icon = iconint;

        String tempsM = (String) icones.get("description");

        temps = tempsM;

        Double tempint = (Double) main.get("temp");

        tempint = tempint - 273;

        tempint = Math.round(tempint * 10d) / 10d;

        temp = tempint;

        System.out.println(temp);

        Meteo meteos = new Meteo(coucher, humidite, icon, lever, temp, temps);

        String jsonOutput = genson.serialize(meteos);

        return meteos;


    }

}
