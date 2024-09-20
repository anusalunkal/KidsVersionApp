package com.nextgen.storytellingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nextgen.storytellingapp.GlobalPreference;
import com.nextgen.storytellingapp.ModelClass.StoryModelClass;
import com.nextgen.storytellingapp.R;
import com.nextgen.storytellingapp.SplashActivity;
import com.nextgen.storytellingapp.StoriesActivity;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder>{

    ArrayList<StoryModelClass> list;
    Context context;
    String ip;
    GlobalPreference globalPreference;


    public StoryAdapter(ArrayList<StoryModelClass> list, Context context) {
        this.list = list;
        this.context = context;

        globalPreference = new GlobalPreference(context);
        ip = globalPreference.getIP();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_story,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StoryModelClass storyList = list.get(position);
        holder.titleTV.setText(storyList.getTitle());
        holder.descTV.setText(storyList.getDesc());

        Glide.with(context).load("http://" + ip +"/kids_stories/story_tbl/uploads/" + storyList.getImage()).into(holder.storyIV);

        holder.storyCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context.getApplicationContext(), StoriesActivity.class);
                intent.putExtra("storyId",storyList.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV;
        TextView descTV;
        ImageView storyIV;
        CardView storyCV;


        public MyViewHolder(@NonNull View itemview) {
            super(itemview);

            titleTV = itemview.findViewById(R.id.storyTitleTextView);
            descTV = itemview.findViewById(R.id.storyDescTextView);
            storyIV = itemview.findViewById(R.id.storyImageView);
            storyCV = itemview.findViewById(R.id.storyCardView);

        }
    }
}
