package com.vukihai.unisecchatbot.ui.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.vukihai.unisecchatbot.R;
import com.vukihai.unisecchatbot.data.model.ChatMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class ChatFragment extends Fragment {
    private ChatMessagesAdapter chatMessagesAdapter;
    private ListView chatMessagesListView;
    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("http://192.168.2.111:5005");
        } catch (URISyntaxException e){

        }
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        chatMessagesAdapter = new ChatMessagesAdapter(getContext());
        chatMessagesListView = root.findViewById(R.id.lv_chat_messages);
        chatMessagesListView.setAdapter(chatMessagesAdapter);
        mSocket.on("bot_uttered",onNewMessage);
        mSocket.connect();
        return root;
    }
    public void send(String message){
        this.chatMessagesAdapter.addMessage(new ChatMessage(message, false));
        JSONObject sendData = new JSONObject();
        try {
            sendData.put("message", message);
            sendData.put("sender", "123");
        } catch (JSONException e) {

        }
        Log.d("vukihai", sendData.toString());
        mSocket.emit("user_uttered", sendData);
    }
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String mess;
                    try{
                        mess = data.getString("text");
                        chatMessagesAdapter.addMessage(new ChatMessage(mess, true));
                    } catch (JSONException e){

                    }
                }
            });
        }
    };
}