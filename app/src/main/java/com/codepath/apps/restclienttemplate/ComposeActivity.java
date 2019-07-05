package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


public class ComposeActivity extends AppCompatActivity {
    private TwitterClient client;
    @BindView(R.id.etNewTweet) EditText etNewTweet;
    @BindView(R.id.tvCharacters) TextView tvCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        ButterKnife.bind(this);

        client = TwitterApp.getRestClient(this);

        Intent i = getIntent();
        Boolean reply = i.getBooleanExtra("REPLY", false);

        //getting the @ handle (screen name)
        if (reply){
            String screenName = i.getStringExtra("SCREEN_NAME");
            etNewTweet.setText("@" + screenName);
        }

        etNewTweet.addTextChangedListener(mTextEditorWatcher);
    }

    public void onSaveTweet(View v) {

        String newTweet = etNewTweet.getText().toString();

        // handle click here
        client.sendTweet(newTweet, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tweet newTweet = Tweet.fromJSON(response);
                    Intent i = new Intent();
                    i.putExtra("NEW_TWEET", Parcels.wrap(newTweet));
                    setResult(RESULT_OK, i);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                throwable.printStackTrace();
            }

        });
    }


    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            String remaining = String.valueOf(280 - s.length());
            tvCharacters.setText(remaining);
        }

        public void afterTextChanged(Editable s) {
        }
    };
}
