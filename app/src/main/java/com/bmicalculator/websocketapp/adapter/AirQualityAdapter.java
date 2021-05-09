package com.bmicalculator.websocketapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bmicalculator.websocketapp.R;
import com.bmicalculator.websocketapp.model.AirQuality;

import java.util.List;

public class AirQualityAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<AirQuality> qualityList;

    public AirQualityAdapter(Context context, List<AirQuality> qualityList) {
        this.context = context;
        this.qualityList = qualityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.quality_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            populateView((MyViewHolder) holder, position);
        }

    }

    private void populateView(MyViewHolder holder, int position) {

        holder.txt_city_name.setText(qualityList.get(position).getCity());
        holder.txt_air_quality.setText(String.format("%.2f", (qualityList.get(position).getAqi())));

        if (qualityList.get(position).getAqi() >= 0 && qualityList.get(position).getAqi() <= 50) {
            holder.txt_air_quality.setTextColor(context.getResources().getColor(R.color.green));
        } else if (qualityList.get(position).getAqi() >= 51 && qualityList.get(position).getAqi() <= 100) {
            holder.txt_air_quality.setTextColor(context.getResources().getColor(R.color.lightgreen));
        } else if (qualityList.get(position).getAqi() >= 101 && qualityList.get(position).getAqi() <= 200) {
            holder.txt_air_quality.setTextColor(context.getResources().getColor(R.color.yellow));
        } else if (qualityList.get(position).getAqi() >= 201 && qualityList.get(position).getAqi() <= 300) {
            holder.txt_air_quality.setTextColor(context.getResources().getColor(R.color.orange));
        } else if (qualityList.get(position).getAqi() >= 300 && qualityList.get(position).getAqi() <= 400) {
            holder.txt_air_quality.setTextColor(context.getResources().getColor(R.color.red));
        } else if (qualityList.get(position).getAqi() >= 400 && qualityList.get(position).getAqi() <= 500) {
            holder.txt_air_quality.setTextColor(context.getResources().getColor(R.color.dark_red));
        }

    }

    @Override
    public int getItemCount() {
        return qualityList.size();
    }

    public void add(List<AirQuality> qualityList) {

        qualityList.addAll(qualityList);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_city_name;
        TextView txt_air_quality;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_city_name = itemView.findViewById(R.id.txt_city_name);
            txt_air_quality = itemView.findViewById(R.id.txt_air_quality);


        }

    }


}
