package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.TimeFormatter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        TextView tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        TextView tvBody = (TextView) findViewById(R.id.tvBody);
        TextView tvCreatedAt = (TextView) findViewById(R.id.tvCreatedAt);


        Tweet tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("TWEET"));

        tvUsername.setText(tweet.user.name);
        tvScreenName.setText("@" + tweet.user.screenName);
        tvBody.setText(tweet.body);
        tvCreatedAt.setText(TimeFormatter.getTimeDifference(tweet.createdAt));

        Glide.with(this)
                .load(tweet.user.profileImageURL)
                .apply(new RequestOptions()
                        .transform(new RoundedCornersTransformation(100, 0, RoundedCornersTransformation.CornerType.ALL)))
                .into(ivProfileImage);
    }
}
