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
import com.vukihai.unisecchatbot.MainActivity;
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
        try {
            mSocket = IO.socket("http://192.168.2.111:5005");

        } catch (URISyntaxException e) {

        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        chatMessagesAdapter = new ChatMessagesAdapter(getContext());
        chatMessagesListView = root.findViewById(R.id.lv_chat_messages);
        chatMessagesListView.setAdapter(chatMessagesAdapter);
        mSocket.on("bot_uttered", onNewMessage);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.connect();
        return root;
    }

    public void send(final String message) {
        JSONObject sendData = new JSONObject();
        try {
            sendData.put("message", message);
            sendData.put("sender", "123");
        } catch (JSONException e) {

        }
        mSocket.emit("user_uttered", sendData);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatMessagesAdapter.addMessage(new ChatMessage(message, false));
                chatMessagesListView.setSelection(chatMessagesAdapter.getCount());
            }
        });
    }
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity) getActivity()).updateNetworkStatus(getContext().getString(R.string.connected), View.GONE);
                }
            });
        }
    };
    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity) getActivity()).updateNetworkStatus(getContext().getString(R.string.connect_error), View.VISIBLE);
                }
            });

        }
    };
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
//                    for( int i = 0; i <args.length; i++) {
//                        Log.d("vukihai", args[i].toString());
//                    }
                        JSONObject data = (JSONObject) args[0];
                        try {
                            Log.d("vukihai", data.toString());
                            final String mess = data.getString("text");
                            if (mess != null) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        chatMessagesAdapter.addMessage(new ChatMessage(mess, true));
                                        chatMessagesListView.setSelection(chatMessagesAdapter.getCount() - 1);
                                    }
                                });
                                return;
                            }
                        } catch (JSONException e) {
                            Log.d("vukihai", e.toString());

                        }
                        try {
                            final String table = data.getJSONArray("table").toString();
                            Log.d(" ", table);
                            if (table.length() != 0) {
                                final ChatMessage mes = new ChatMessage(table, true);
                                mes.setBotTable(true);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        chatMessagesAdapter.addMessage(mes);
                                        chatMessagesListView.setSelection(chatMessagesAdapter.getCount() - 1);
                                    }
                                });
                                return;
                            }
                        } catch (JSONException e) {
                            Log.d("vukihai", e.toString());
                        }
                        try {
                            final String slider = data.getJSONArray(("slider")).toString();
                            if (slider != null) {
                                final ChatMessage mes = new ChatMessage(slider, true);
                                mes.setBotSlider(true);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        chatMessagesAdapter.addMessage(mes);
                                        chatMessagesListView.setSelection(chatMessagesAdapter.getCount() - 1);
                                    }
                                });
                                return;
                            }
                        } catch (JSONException e) {
                            Log.d("vukihai", e.toString());

                        }
                        try {
                            final String slider = data.getJSONArray(("htmlview")).toString();
                            if (slider != null) {
                                final ChatMessage mes = new ChatMessage(slider, true);
                                mes.setBotHtmlview(true);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        chatMessagesAdapter.addMessage(mes);
                                        chatMessagesListView.setSelection(chatMessagesAdapter.getCount() - 1);
                                    }
                                });
                                return;
                            }
                        } catch (JSONException e) {
                            Log.d("vukihai", e.toString());

                        }
                    } catch (Exception e) {
                        Log.d("vukihai", e.toString());
                    }
                }
            });
        }
    };
}