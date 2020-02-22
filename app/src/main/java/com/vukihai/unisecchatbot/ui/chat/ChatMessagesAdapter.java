package com.vukihai.unisecchatbot.ui.chat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vukihai.unisecchatbot.R;
import com.vukihai.unisecchatbot.data.model.ChatMessage;
import java.util.ArrayList;
import java.util.List;

public class ChatMessagesAdapter extends BaseAdapter {
    private List<ChatMessage> chatMessages;
    private Context context;
    public ChatMessagesAdapter(Context c) {
        context = c;
        this.chatMessages = new ArrayList<>();
        this.chatMessages.add(new ChatMessage("xin chào. tôi là user", false));
        this.chatMessages.add(new ChatMessage("xin chào. tôi là bot", true));
    }
    public void addMessage(ChatMessage message){
        this.chatMessages.add(message);
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return this.chatMessages.size();
    }

    @Override
    public Object getItem(int i) {
        return this.chatMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        ChatMessage message = this.chatMessages.get(i);
        if(message.isBotMessage()){
            view = layoutInflater.inflate(R.layout.bot_message, null);
            TextView messTextView = view.findViewById(R.id.tv_bot_message);
            messTextView.setText(message.getText());
        } else {
            view = layoutInflater.inflate(R.layout.user_message, null);
            TextView messTextView = view.findViewById(R.id.tv_user_message);
            messTextView.setText(message.getText());
        }
        return view;
    }
}
