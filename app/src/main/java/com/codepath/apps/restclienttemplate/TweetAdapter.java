package com.codepath.apps.restclienttemplate;

//THE STEPS - creating the adapter
//create ViewHolder class
//pass in the tweets array into the constructor
//for each row, inflate the layout and pass into ViewHolder class -- onCreateViewHolder
//for each row we are showing, we need to bind the value of the tweet object to that element -- onBindViewHolder
//dont forget to create and change getItemCount!!

//later add the profile image using Glide


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.TimeFormatter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{

    //pass in the tweets array into the constructor
    //mTweets is just a reference to tweets
    private List <Tweet> mTweets;

    public TweetAdapter(List<Tweet> tweets){
        mTweets = tweets;
    }

    //context to be used in onCreateVH & onBind
    Context context;

    //for each row, inflate the layout and pass into ViewHolder class
    //only invoked when a new row needs to be created, otherwise, the adapter will call onbindVH as the user scrolls down
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Context context = viewGroup.getContext(); --> we need this later in onBindViewHolder though,
                                                      //so instead make it a public variable for the whole class
        context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    //for each row we are showing, we need to bind the value of the tweet object to that element
    //as the user scrolls down, repopulate viewholder based on position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //get the data according to position (i)
        Tweet tweet = mTweets.get(i);

        //populate the views according to this data
        viewHolder.tvUserName.setText(tweet.user.name);
        viewHolder.tvScreenName.setText("@" + tweet.user.screenName);
        viewHolder.tvCreatedAt.setText(TimeFormatter.getTimeDifference(tweet.createdAt));
        viewHolder.tvBody.setText(tweet.body);

        Glide.with(context)
                .load(tweet.user.profileImageURL)
                .apply(new RequestOptions()
                .transform(new RoundedCornersTransformation(100, 0, RoundedCornersTransformation.CornerType.ALL)))
                .into(viewHolder.ivProfileImage);
    }


    //how many tweets to display
    @Override
    public int getItemCount() {
        return mTweets.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivProfileImage;
        public TextView tvUserName;
        public TextView tvScreenName;
        public TextView tvCreatedAt;
        public TextView tvBody;


        public ViewHolder(View itemView){
            super(itemView);

            //perform find view by id lookups

            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUsername);
            tvScreenName = (TextView) itemView.findViewById(R.id.tvScreenName);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.tvCreatedAt);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Tweet tweet = mTweets.get(position);
                // Intent i = new Intent(this.context, BookDetailActivity.class);
                //                i.putExtra("BOOK", Parcels.wrap(book));
                //                context.startActivity(i);
                //TODO: put intent to tweet detail activity
            }
        }

    }


    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }
}
