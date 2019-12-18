package com.svmc.unisec_chatbot;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {
    private List<ChatMessage> messages = new ArrayList<>();
    private Context mContext;
    public MessageAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void add(ChatMessage msg){
        messages.add(msg);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageViewHolder viewHolder = new MessageViewHolder();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ChatMessage chatMessage = this.messages.get(position);

        if(!chatMessage.isBotMessage()){
            convertView = inflater.inflate(R.layout.item_my_message, null);
            viewHolder.message = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(viewHolder);
            viewHolder.message.setText(chatMessage.getText());
        } else {
            convertView = inflater.inflate(R.layout.item_bot_message, null);
            viewHolder.avatar = (View) convertView.findViewById(R.id.avatar);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.message = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(viewHolder);

            viewHolder.name.setText("unisec-chatbot");
            viewHolder.message.setText(chatMessage.getText());
            GradientDrawable drawable = (GradientDrawable) viewHolder.avatar.getBackground();
        }
        return convertView;
    }
    class MessageViewHolder{
        public View avatar;
        public TextView name;
        public TextView message;
    }
}
