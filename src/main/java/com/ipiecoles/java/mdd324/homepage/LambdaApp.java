package com.ipiecoles.java.mdd324.homepage;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LambdaApp implements RequestHandler<Map<String,Object>, GatewayResponse> {


    @Override
    public GatewayResponse handleRequest(Map<String, Object> input, final Context context) {
        Meteo meteo = null;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        Genson genson = new GensonBuilder().useRuntimeType(true).create();
        Ville ville = genson.deserialize((String)input.get("body"), Ville.class);

        try {
            meteo = MeteoService.getMeteo(ville);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new GatewayResponse("{'error':'Erreur interne'}",
                    headers, 500);
        }

        String jsonOutput = genson.serialize(meteo);
        GatewayResponse gatewayResponse = new GatewayResponse(jsonOutput,headers, 200);
        return gatewayResponse;
    }
}
