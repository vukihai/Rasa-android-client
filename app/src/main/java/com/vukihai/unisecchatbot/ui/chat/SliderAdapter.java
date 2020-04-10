package com.vukihai.unisecchatbot.ui.chat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vukihai.unisecchatbot.MainActivity;
import com.vukihai.unisecchatbot.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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
                sliderItems.add(new SliderItem(obj.getString("name"), obj.getString("payload"), obj.getString("img")));
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
        new DownloadImageTask(((RecyclerViewHolder) holder).sliderImageView).execute(this.sliderItems.get(position).getImg());
    }
    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView sliderTextView;
        ImageView sliderImageView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            sliderTextView = itemView.findViewById(R.id.text_slider_item);
            sliderImageView = itemView.findViewById(R.id.img_slider_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)context).sendMessage(sliderItems.get(getAdapterPosition()).getPayload());

                }
            });
        }
    }

    private class SliderItem {
        private String name, payload, img;

        public SliderItem(String name, String payload, String img) {
            this.name = name;
            this.payload = payload;
            this.img = img;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
    static private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            String urlStr = params[0];
            Bitmap img = null;

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(urlStr);
            HttpResponse response;
            try {
                response = (HttpResponse)client.execute(request);
                HttpEntity entity = response.getEntity();
                BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
                InputStream inputStream = bufferedEntity.getContent();
                img = BitmapFactory.decodeStream(inputStream);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return img;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }}
}