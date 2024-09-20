package com.nextgen.storytellingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nextgen.storytellingapp.CharacterActivity;
import com.nextgen.storytellingapp.GlobalPreference;
import com.nextgen.storytellingapp.ModelClass.CharacterModelClass;
import com.nextgen.storytellingapp.R;
import com.nextgen.storytellingapp.SoundActivity;
import com.nextgen.storytellingapp.SplashActivity;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder>{

    ArrayList<CharacterModelClass> list;
    Context context;
    String ip;
    private int selectedPosition = -1;
    GlobalPreference globalPreference;

    public CharacterAdapter(ArrayList<CharacterModelClass> list, Context context) {
        this.list = list;
        this.context = context;

        globalPreference = new GlobalPreference(context);
        ip = globalPreference.getIP();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_character,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CharacterModelClass characterList = list.get(position);
        holder.nameTV.setText(characterList.getName());

        Glide.with(context).load("http://" + ip +"/kids_stories/characters_tbl/uploads/" + characterList.getImage()).into(holder.characterIV);

        if (position == selectedPosition) {
            holder.characterLL.setBackgroundResource(R.drawable.card_bg_selected);
        } else {
            holder.characterLL.setBackgroundResource(R.drawable.card_bg);
        }

        holder.characterCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();

                globalPreference.saveCharacterSelected(true);
                globalPreference.saveCharacter(characterList.getName());
                globalPreference.saveCharacterImage(characterList.getImage());


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV;
        ImageView characterIV;
        CardView characterCV;
        LinearLayout characterLL;

        public MyViewHolder(@NonNull View itemview) {
            super(itemview);

         nameTV = itemview.findViewById(R.id.characterNameTextView);
         characterIV =itemview.findViewById(R.id.characterIconImageView);
         characterCV = itemview.findViewById(R.id.card_character);
         characterLL = itemview.findViewById(R.id.characterLL);

        }
    }
}
