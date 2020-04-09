package com.vukihai.unisecchatbot.ui.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vukihai.unisecchatbot.MainActivity;
import com.vukihai.unisecchatbot.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class SliderAdapter extends RecyclerView.Adapter {
    private List<SliderItem> sliderItems;
    Context context;
    public SliderAdapter(String json, Context context) {
        this.context = context;
        sliderItems = new ArrayList<>();
        try {
            JSONArray jsonArr = new JSONArray(json);
            for(int i=0; i< jsonArr.length(); i++){
                JSONObject obj = jsonArr.getJSONObject(i);
                sliderItems.add(new SliderItem(obj.getString("name"), obj.getString("payload")));
            }
        } catch (JSONException e){
            Log.d("vukihai", "error while parse json: " + e.toString());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.subitem_bot_slider, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecyclerViewHolder) holder).sliderTextView.setText(this.sliderItems.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView sliderTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            sliderTextView = (TextView) itemView.findViewById(R.id.text_slider_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)context).sendMessage(sliderItems.get(getAdapterPosition()).getPayload());

                }
            });
        }
    }

    private class SliderItem {
        String name, payload;
        // image

        public SliderItem(String name, String payload) {
            this.name = name;
            this.payload = payload;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }
    }
}