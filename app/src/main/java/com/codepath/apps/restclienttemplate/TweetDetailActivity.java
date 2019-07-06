package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.TimeFormatter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvBody) TextView tvBody;
    @BindView(R.id.tvCreatedAt) TextView tvCreatedAt;
    @BindView(R.id.ivEntityTweet) ImageView ivEntityTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        ButterKnife.bind(this);

        //changing the action bar color to Twitter's signature blue ;)
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.twitter_color)));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setLogo(getResources().getDrawable(R.drawable.ic_icon));
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

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

        if (tweet.hasEntities == true){
            Glide.with(this)
                    .load(tweet.entity.loadURL)
                    .apply(new RequestOptions()
                            .transform(new RoundedCornersTransformation(30, 0, RoundedCornersTransformation.CornerType.ALL)))
                    .into(ivEntityTweet);
            ivEntityTweet.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.ibReply)
    public void onClickReply(View view) {
        String screenName = tvScreenName.getText().toString();
        Intent i = new Intent(this, ComposeActivity.class);
        i.putExtra("REPLY", true);
        i.putExtra("SCREEN_NAME", screenName);
        startActivityForResult(i,20);
    }
}
