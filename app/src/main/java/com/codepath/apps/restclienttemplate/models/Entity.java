package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Entity {
    public String loadURL;

    public Entity(){

    }
    public static Entity fromJSON(JSONObject jsonObject) throws JSONException {
        Entity entity = new Entity();
        entity.loadURL = jsonObject.getJSONArray("media").getJSONObject(0).getString("media_url_https"); //navigating the JSON file to get to the value we need
        return entity;
    }
}

