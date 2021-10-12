package com.codecool.shop.utill;

import com.codecool.shop.utill.Message;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class HandlingJSonObject {
    Gson gson = new Gson();

    public JSONObject getJsonFromRequest(HttpServletRequest req) {
        String jsonBody = null;
        try {
            jsonBody = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(
                    Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonBody == null || jsonBody.trim().length() == 0) {
            System.out.println("Request is null");
        }
        JSONObject jObj = new JSONObject(jsonBody);
        return jObj;
    }

    public String makeJsonResponse(String key, String value)  {
        Message message = new Message();
        message.setKey(key);
        message.setValue(value);
        String jsonString = gson.toJson(message);
        return jsonString;
    }
}
