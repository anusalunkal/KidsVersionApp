package com.nextgen.storytellingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.nextgen.storytellingapp.GlobalPreference;
import com.nextgen.storytellingapp.ModelClass.CharacterModelClass;
import com.nextgen.storytellingapp.ModelClass.PlotModelClass;
import com.nextgen.storytellingapp.R;
import com.nextgen.storytellingapp.StoryListActivity;

import java.util.ArrayList;

public class PlotAdapter extends RecyclerView.Adapter<PlotAdapter.MyViewHolder>{

    ArrayList<PlotModelClass> list;
    Context context;
    String ip;
    GlobalPreference globalPreference;

    private int selectedPosition = -1;


    public PlotAdapter(ArrayList<PlotModelClass> list, Context context) {
        this.list = list;
        this.context = context;

        globalPreference = new GlobalPreference(context);
        ip = globalPreference.getIP();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_plot,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PlotModelClass plotList = list.get(position);

        if (position == selectedPosition) {
            holder.plotLL.setBackgroundResource(R.drawable.card_bgg);
            holder.plotTV.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.plotLL.setBackgroundResource(R.drawable.card_bg_selected);
            holder.plotTV.setTextColor(ContextCompat.getColor(context, R.color.blue));
        }

        //if the plot contains a space, it will split into two lines at the space
        String plot = plotList.getPlot();
        if (plot.contains(" ")) {
            holder.plotTV.setText(plot.replaceFirst(" ", "\n"));
        } else {
            holder.plotTV.setText(plot);
        }

        holder.plotLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();

                globalPreference.savePlotSelected(true);
                globalPreference.savePlot(plotList.getPlot());




            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView plotTV;
        LinearLayout plotLL;
        public MyViewHolder(@NonNull View itemview) {
            super(itemview);

            plotTV = itemview.findViewById(R.id.plotTextView);
            plotLL = itemview.findViewById(R.id.plotLinearLayout);

        }
    }
}
