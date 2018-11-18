package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String JSON_NAME = "name";
    private static final String JSON_MNAME = "mainName";
    private static final String JSON_AKA = "alsoKnownAs";
    private static final String JSON_PLORIGIN = "placeOfOrigin";
    private static final String JSON_DESC = "description";
    private static final String JSON_IMAGE = "image";
    private static final String JSON_INGDS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject contact = new JSONObject(json);

            //Name into a JSONObject
            JSONObject name = contact.optJSONObject(JSON_NAME);
            String mainName = name.optString(JSON_MNAME, "Sandwich");
            JSONArray aka = name.optJSONArray(JSON_AKA); //try opt instead of getJSONArray suggestion on stack OverFlow

            List<String> alsoKnownAs = new ArrayList<>();

            for (int i = 0; i < aka.length(); i++) {
                alsoKnownAs.add(aka.getString(i));
            }

            String placeOfOrigin = contact.optString(JSON_PLORIGIN, "Unknown");
            String description = contact.optString(JSON_DESC, "Description not available");
            String image = contact.optString(JSON_IMAGE, "Image not available");

            JSONArray ingds = contact.optJSONArray(JSON_INGDS);
            List<String> ingredients = new ArrayList<>();

            for (int j = 0; j < ingds.length(); j++) {
                ingredients.add(ingds.getString(j));
            }

            System.out.print(sandwich.toString());
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
