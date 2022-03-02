package com.ipiecoles.java.mdd324.homepage;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LambdaApp implements RequestHandler<Ville, GatewayResponse> {


    @Override
    public GatewayResponse handleRequest(Ville ville, Context context) {
        Meteo meteo = null;

        Genson genson = new GensonBuilder().useRuntimeType(true).create();
        String jsonOutput = genson.serialize(meteo);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        try {
            meteo = MeteoService.getMeteo(ville);
        } catch (IOException e) {
            return new GatewayResponse("{'error':'Erreur interne'}",
                    headers, 500);
        }
        GatewayResponse gatewayResponse = new GatewayResponse(jsonOutput,headers, 200);
        return gatewayResponse;
    }
}
