package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.restclienttemplate.models.ComposeActivity;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

//THE STEPS - setting up the adapter
//create variables for the adapter, data source, and recyclerview (MVC)
//in onCreate: construct the adapter from the datasource, then set the layout manager & adapter
//in onSuccess under populateTimeline: iterate through the JSONArray and for each entry deserialize
//                                     the JSON object, adding to data source and notifying adapter



public class TimelineActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;

    private TwitterClient client;
    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;//our data source
    RecyclerView rvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //first clear everything out
                tweetAdapter.clear();
                //repopulate
                populateTimeline();
                //now make sure swipeContainer.setRefreshing is set to false
                //but let's not do that here becauuuuuse.... ASYNCHRONOUS
                //lets put it at the end of populateTimeline instead!
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        //changing the action bar color to Twitter's signature blue ;)
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.twitter_color)));

        client = TwitterApp.getRestClient(this);
        //find the RecyclerView
        rvTweets = (RecyclerView) findViewById(R.id.rvTweet);
        //init the arraylist (data source)
        tweets = new ArrayList<>();
        //construct the adapter from the data source
        tweetAdapter = new TweetAdapter(tweets);
        //RecyclerView setup (layout manager, use adapter), pass in the context!
        //the layout manager needs the context to know what kinds of settings to use
        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        //set the adapter
        rvTweets.setAdapter(tweetAdapter);

        populateTimeline();
    }




    private final int REQUEST_CODE = 20;
    public void onComposeAction(MenuItem mi) {
        // handle click here
        Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // check request code and result code first
//        if (resultCode == )

        // Use data parameter
        Tweet newTweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("NEW_TWEET"));
        tweets.add(0, newTweet);
        tweetAdapter.notifyItemInserted(0);
        rvTweets.scrollToPosition(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    private void populateTimeline(){
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                Log.d("TwitterClient", response.toString());
                //iterate through the JSONArray and for each entry deserialize the JSON object
                for (int i = 0; i < response.length(); i++){
                    //put a try catch since its a response!
                    try{
                        //convert each object to a Tweet model
                        Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
                        //add that tweet model to the data source
                        tweets.add(tweet);
                        //notify the adapter that we've added an item
                        tweetAdapter.notifyItemInserted(response.length() - 1);
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                    //Now we call setRefreshing(false) to signal refresh has finished
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TwitterClient", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

        });
    }
}

