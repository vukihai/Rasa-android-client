package com.vukihai.unisecchatbot.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.vukihai.unisecchatbot.R;

import java.util.List;

class SliderAdapter extends RecyclerView.Adapter{
    private List<Pair<String, String>> content;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.subitem_bot_slider, parent, false);
        return new RecyclerViewHolder(view);
    }
    void setContent(String json){

    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecyclerViewHolder)holder).sliderTextView.setText("test");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView sliderTextView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            sliderTextView = (TextView) itemView.findViewById(R.id.text_slider_item);
        }
    }
}