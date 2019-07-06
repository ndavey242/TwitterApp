package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;


@Parcel
public class Tweet {
    //list out the attributes
    public String body;
    public long uid; //database ID for the tweet
    public User user;
    public String createdAt;
    //for loading media entity
    public boolean hasEntities;
    public Entity entity;

    //deserialize the JSON data
    public static Tweet fromJSON (JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        //extract the values from JSON
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        //going to the User class to extract data from the JSONObject "user" within jsonObject
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));


        JSONObject entityObject = jsonObject.getJSONObject("entities");
        if (entityObject.has("media")){
            JSONArray mediaEndpoint = entityObject.getJSONArray("media");
            if (mediaEndpoint != null && mediaEndpoint.length() != 0){
                tweet.entity = Entity.fromJSON(jsonObject.getJSONObject("entities"));
                tweet.hasEntities = true;
            }
            else{tweet.hasEntities = false;}
        }

        return tweet;
    }


    //required by Parcel
    public Tweet(){

    }
}
