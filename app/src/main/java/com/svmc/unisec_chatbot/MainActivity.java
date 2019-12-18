package com.svmc.unisec_chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.svmc.unisec_chatbot.model.ChatRoom;
import com.svmc.unisec_chatbot.model.ChatRoomListener;

public class MainActivity extends AppCompatActivity implements ChatRoomListener{
    EditText inputMessageEditText;
    MessageAdapter messageAdapter;
    ListView messagesListView;
    ChatRoom mChatRoom;
    private String serverAddr ="http://192.168.2.111:5005";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // try connect to server
        mChatRoom = new ChatRoom(this, this.serverAddr, "1");
        mChatRoom.connect();

        messageAdapter = new MessageAdapter(this);
        messagesListView.setAdapter(messageAdapter);
    }

    private void init() {
        inputMessageEditText = findViewById(R.id.edt_input_message);
        messagesListView = findViewById(R.id.list_messages);
    }

    @Override
    public void onConnectSuccess(String msg) {
        Log.d("vukihai", msg);
    }

    @Override
    public void onConnectFail(String msg) {
        Log.d("vukihai", msg);
    }

    @Override
    public void onMessageSuccess(String msg) {
        Log.d("vukihai" , msg);
        final String message = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(new ChatMessage(message, true));
                // scroll the ListView to the last added element
                messagesListView.setSelection(messagesListView.getCount() - 1);
            }
        });
    }

    @Override
    public void onMessageFail(String error) {
        Log.d("vukihai", error);
    }

    public void onSendButtonClick(View v) {
        String message = inputMessageEditText.getText().toString();
        if(message.length() > 10 && message.substring(0,7).equals("server:")){
            this.serverAddr = "http://" + message.substring(7);
            Log.d("vukihai", this.serverAddr);
            this.mChatRoom.setApiServerAddr(this.serverAddr);
        }
        if(message.length() >0){
            messageAdapter.add(new ChatMessage(message, false));
            mChatRoom.sendMessage(message);
            inputMessageEditText.getText().clear();
        }
    }
}
