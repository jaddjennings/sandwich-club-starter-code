package com.udacity.sandwichclub.utils;

import android.util.JsonReader;

import com.udacity.sandwichclub.model.Sandwich;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static ArrayList<String> convertJSONArrayToList(JSONArray jsonArray) throws JSONException{
        ArrayList<String> retList = new ArrayList<>();
        for (int i = 0; i<jsonArray.length();i++) {
            retList.add(jsonArray.getString(i));
        }
        return retList;
    }

    public static Sandwich parseSandwichJson(String json) {
        JSONObject js = new JSONObject();
        JSONObject js_sublevel = new JSONObject();
        Sandwich ret = new Sandwich();

         try {
              js = new JSONObject(json);
             js_sublevel = js.getJSONObject("name");
              String mnName ="";
              if(!js_sublevel.isNull("mainName"))
                  mnName=js_sublevel.getString("mainName");
              String placeOfOrigin = "";
              if(!js.isNull("placeOfOrigin"))
                  placeOfOrigin=js.getString("placeOfOrigin");
              String description = "";
              if(!js.isNull("description"))
                 description=js.getString("description");
              String image ="";
              if(!js.isNull("image"))
                  image=js.getString("image");
              ArrayList<String> aka = new ArrayList<>();
              ArrayList<String> ingr = new ArrayList<>();
              if(!js_sublevel.isNull("alsoKnownAs"))
                 aka = convertJSONArrayToList((JSONArray)js_sublevel.get("alsoKnownAs"));
              if(!js.isNull("ingredients"))
                 ingr = convertJSONArrayToList((JSONArray)js.get("ingredients"));
             /**Completed: return Sandwich object and fix construction**/
                  ret = new Sandwich( mnName
                                     ,aka
                                     ,placeOfOrigin
                                     ,description
                                     ,image
                                     ,ingr
                                    );
        }
        catch (Exception e){

            System.out.println(e.getMessage());
        }
        return ret;
    }
}
