package com.vukihai.unisecchatbot.ui.chat;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evrencoskun.tableview.TableView;
import com.vukihai.unisecchatbot.R;
import com.vukihai.unisecchatbot.data.model.ChatMessage;
import com.vukihai.unisecchatbot.ui.chat.bot_table_view.BotCell;
import com.vukihai.unisecchatbot.ui.chat.bot_table_view.BotColumnHeader;
import com.vukihai.unisecchatbot.ui.chat.bot_table_view.BotRowHeader;
import com.vukihai.unisecchatbot.ui.chat.bot_table_view.BotTableModel;
import com.vukihai.unisecchatbot.ui.chat.bot_table_view.TableAdapter;
import com.vukihai.unisecchatbot.ui.chat.bot_table_view.TableListener;

import org.json.JSONArray;
import org.json.JSONException;

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
        ChatMessage ch = new ChatMessage("<p style=\"\"text-align: justify;\"\"><strong>Mã trường: DDB</strong></p><p style=\"\"text-align: justify;\"\">Địa chỉ: Trụ sở chính: Số 3 Vũ Công Đán, phường Tứ Minh, TP Hải Dương</p><p style=\"\"text-align: justify;\"\">Tel: 03203 559 666 - 03203 559 559</p><p style=\"\"text-align: justify;\"\">Cơ sở 2: Km 50 quốc lộ 5 Đường An Định, Phường Việt Hòa, TP Hải Dương</p><p style=\"\"text-align: justify;\"\">Tel: 03203 680 186 - Fax: 03203 680 222</p><p style=\"\"text-align: justify;\"\" align=\"\"center\"\"><strong>Thông tin tuyển sinh năm 2019:&nbsp;</strong></p><table border=\"\"1\"\" cellspacing=\"\"0\"\" cellpadding=\"\"0\"\"><tbody><tr><td class=\"\"xl79\"\" rowspan=\"\"2\"\" width=\"\"179\"\" height=\"\"103\"\"><strong>Ngành học</strong></td><td class=\"\"xl66\"\" rowspan=\"\"2\"\" width=\"\"64\"\"><strong>Mã ngành</strong></td><td class=\"\"xl65\"\" colspan=\"\"2\"\" width=\"\"128\"\"><strong>Chỉ tiêu (dự kiến)</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Tổ hợp môn xét tuyển 1</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Tổ hợp môn xét tuyển 2</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Tổ hợp môn xét tuyển 3</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Tổ hợp môn xét tuyển 4</strong></td></tr><tr><td class=\"\"xl65\"\" width=\"\"64\"\" height=\"\"69\"\"><strong>Theo xét KQ thi THPT QG</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Theo phương thức khác</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Mã tổ hợp môn</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Mã tổ hợp môn</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Mã tổ hợp môn</strong></td><td class=\"\"xl65\"\" width=\"\"64\"\"><strong>Mã tổ hợp môn</strong></td></tr><tr><td class=\"\"xl72\"\" height=\"\"21\"\">Các ngành đào tạo đại học</td><td class=\"\"xl69\"\" width=\"\"64\"\">&nbsp;</td><td class=\"\"xl71\"\">&nbsp;</td><td class=\"\"xl70\"\">&nbsp;</td><td class=\"\"xl72\"\">&nbsp;</td><td class=\"\"xl72\"\">&nbsp;</td><td class=\"\"xl72\"\">&nbsp;</td><td class=\"\"xl72\"\">&nbsp;</td></tr><tr><td class=\"\"xl72\"\" height=\"\"21\"\">Chính trị học</td><td class=\"\"xl69\"\" align=\"\"right\"\" width=\"\"64\"\">7310201</td><td class=\"\"xl71\"\">10</td><td class=\"\"xl71\"\">90</td><td class=\"\"xl72\"\">A00</td><td class=\"\"xl72\"\">C00</td><td class=\"\"xl72\"\">C03</td><td class=\"\"xl72\"\">C04</td></tr><tr><td class=\"\"xl75\"\" height=\"\"21\"\">Quản lý nhà nước</td><td class=\"\"xl73\"\" align=\"\"right\"\" width=\"\"64\"\">7310205</td><td class=\"\"xl74\"\">10</td><td class=\"\"xl74\"\">90</td><td class=\"\"xl75\"\">A00</td><td class=\"\"xl75\"\">C00</td><td class=\"\"xl75\"\">C03</td><td class=\"\"xl75\"\">C04</td></tr><tr><td class=\"\"xl72\"\" height=\"\"21\"\">Quản trị kinh doanh</td><td class=\"\"xl69\"\" align=\"\"right\"\" width=\"\"64\"\">7340101</td><td class=\"\"xl71\"\">7</td><td class=\"\"xl71\"\">73</td><td class=\"\"xl72\"\">A00</td><td class=\"\"xl72\"\">A04</td><td class=\"\"xl72\"\">C03</td><td class=\"\"xl72\"\">D01</td></tr><tr><td class=\"\"xl75\"\" height=\"\"21\"\">Tài chính – Ngân hàng</td><td class=\"\"xl73\"\" align=\"\"right\"\" width=\"\"64\"\">7340201</td><td class=\"\"xl74\"\">5</td><td class=\"\"xl74\"\">45</td><td class=\"\"xl75\"\">A00</td><td class=\"\"xl75\"\">A04</td><td class=\"\"xl75\"\">C03</td><td class=\"\"xl75\"\">D01</td></tr><tr><td class=\"\"xl72\"\" height=\"\"21\"\">Kế toán</td><td class=\"\"xl69\"\" align=\"\"right\"\" width=\"\"64\"\">7340301</td><td class=\"\"xl71\"\">8</td><td class=\"\"xl71\"\">62</td><td class=\"\"xl72\"\">A00</td><td class=\"\"xl72\"\">A04</td><td class=\"\"xl72\"\">C03</td><td class=\"\"xl72\"\">D01</td></tr><tr><td class=\"\"xl75\"\" height=\"\"21\"\">Luật kinh tế</td><td class=\"\"xl73\"\" align=\"\"right\"\" width=\"\"64\"\">7380107</td><td class=\"\"xl74\"\">10</td><td class=\"\"xl74\"\">140</td><td class=\"\"xl75\"\">A00</td><td class=\"\"xl75\"\">B00</td><td class=\"\"xl75\"\">B02</td><td class=\"\"xl75\"\">C03</td></tr><tr><td class=\"\"xl72\"\" height=\"\"21\"\">Công nghệ thông tin</td><td class=\"\"xl69\"\" align=\"\"right\"\" width=\"\"64\"\">7480201</td><td class=\"\"xl71\"\">5</td><td class=\"\"xl71\"\">35</td><td class=\"\"xl72\"\">A00</td><td class=\"\"xl72\"\">C03</td><td class=\"\"xl72\"\">D01</td><td class=\"\"xl72\"\">D90</td></tr><tr><td class=\"\"xl75\"\" height=\"\"21\"\">Công nghệ kỹ thuật xây dựng</td><td class=\"\"xl73\"\" align=\"\"right\"\" width=\"\"64\"\">7510103</td><td class=\"\"xl74\"\">5</td><td class=\"\"xl74\"\">35</td><td class=\"\"xl75\"\">A00</td><td class=\"\"xl75\"\">A04</td><td class=\"\"xl75\"\">A05</td><td class=\"\"xl75\"\">D01</td></tr><tr><td class=\"\"xl72\"\" height=\"\"21\"\">Thú y</td><td class=\"\"xl69\"\" align=\"\"right\"\" width=\"\"64\"\">7640101</td><td class=\"\"xl71\"\">5</td><td class=\"\"xl71\"\">35</td><td class=\"\"xl72\"\">A00</td><td class=\"\"xl72\"\">A04</td><td class=\"\"xl72\"\">B00</td><td class=\"\"xl72\"\">B02</td></tr><tr><td class=\"\"xl75\"\" height=\"\"21\"\">Y khoa</td><td class=\"\"xl73\"\" align=\"\"right\"\" width=\"\"64\"\">7720101</td><td class=\"\"xl74\"\">10</td><td class=\"\"xl74\"\">90</td><td class=\"\"xl75\"\">A00</td><td class=\"\"xl75\"\">C00</td><td class=\"\"xl75\"\">C03</td><td class=\"\"xl75\"\">C04</td></tr><tr><td class=\"\"xl72\"\" height=\"\"21\"\">Y học cổ truyền</td><td class=\"\"xl69\"\" align=\"\"right\"\" width=\"\"64\"\">7720115</td><td class=\"\"xl71\"\">20</td><td class=\"\"xl71\"\">180</td><td class=\"\"xl72\"\">A00</td><td class=\"\"xl72\"\">A05</td><td class=\"\"xl72\"\">B00</td><td class=\"\"xl72\"\">B02</td></tr><tr><td class=\"\"xl75\"\" height=\"\"21\"\">Dược học</td><td class=\"\"xl73\"\" align=\"\"right\"\" width=\"\"64\"\">7720201</td><td class=\"\"xl74\"\">15</td><td class=\"\"xl74\"\">135</td><td class=\"\"xl75\"\">A00</td><td class=\"\"xl75\"\">A05</td><td class=\"\"xl75\"\">B00</td><td class=\"\"xl75\"\">B02</td></tr><tr><td class=\"\"xl72\"\" height=\"\"21\"\">Điều dưỡng</td><td class=\"\"xl69\"\" align=\"\"right\"\" width=\"\"64\"\">7720301</td><td class=\"\"xl71\"\">10</td><td class=\"\"xl71\"\">90</td><td class=\"\"xl72\"\">A00</td><td class=\"\"xl72\"\">A05</td><td class=\"\"xl72\"\">B00</td><td class=\"\"xl72\"\">B02</td></tr><tr><td class=\"\"xl75\"\" height=\"\"21\"\">Quản lý đất đai</td><td class=\"\"xl73\"\" align=\"\"right\"\" width=\"\"64\"\">7850103</td><td class=\"\"xl74\"\">10</td><td class=\"\"xl74\"\">90</td><td class=\"\"xl75\"\">A00</td><td class=\"\"xl75\"\">A04</td><td class=\"\"xl75\"\">C03</td><td class=\"\"xl75\"\">D01</td></tr><tr><td class=\"\"xl81\"\" height=\"\"21\"\">&nbsp;</td><td class=\"\"xl78\"\" width=\"\"64\"\"><label>Tổng:</label></td><td class=\"\"xl77\"\" align=\"\"right\"\" width=\"\"64\"\"><strong>130</strong></td><td class=\"\"xl77\"\" align=\"\"right\"\" width=\"\"64\"\"><strong>1.190</strong></td><td class=\"\"xl76\"\" width=\"\"64\"\">&nbsp;</td><td class=\"\"xl76\"\" width=\"\"64\"\">&nbsp;</td><td class=\"\"xl76\"\" width=\"\"64\"\">&nbsp;</td><td class=\"\"xl76\"\" width=\"\"64\"\">&nbsp;</td></tr></tbody></table><p><strong>&gt;&gt; XEM CHI TIẾT PHƯƠNG ÁN TUYỂN SINH CỦA TRƯỜNG <a href=\"\"https://tin.tuyensinh247.com/truong-dai-hoc-thanh-dong-e226.html\"\" target=\"\"_blank\"\">TẠI ĐÂY</a></strong></p><p style=\"\"text-align: justify;\"\" align=\"\"center\"\"><strong><br></strong></p>", true);
        ch.setBotHtmlview(true);
        this.chatMessages.add(ch);
        ChatMessage ch1 = new ChatMessage("[{'name': 'chọn trường', 'img': 'http://www.wikipedia.org/portal/wikipedia.org/assets/img/Wikipedia-logo-v2.png', 'payload':'chọn trường'},{'name': 'chọn ngành', 'img': 'http://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg', 'payload':'chọn ngành'}]", true);
        ch1.setBotSlider(true);
        this.chatMessages.add(ch1);
    }

    public void addMessage(ChatMessage message) {
        try {
            if (this.chatMessages.get(this.chatMessages.size() - 1).isBotSlider())
                this.chatMessages.remove(this.chatMessages.size() - 1);
            this.notifyDataSetChanged();
        } catch (Exception e) {
            Log.d("vukihai", "error while delete slider message");
        }
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

        final ChatMessage message = this.chatMessages.get(i);
        if (message.isBotMessage()) {
            Log.d("vukihai", message.getText());
            if (message.isBotSlider()) {
                view = layoutInflater.inflate(R.layout.item_bot_slider, null);
                RecyclerView recyclerView = view.findViewById(R.id.list_bot_slider);
                SliderAdapter sliderAdapter = new SliderAdapter(message.getText(), context);
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(sliderAdapter);
            } else if (message.isBotHtmlview()) {
                view = layoutInflater.inflate(R.layout.item_bot_htmlview, null);
                final WebView messWebView = view.findViewById(R.id.wv_bot_message);
                messWebView.setFocusable(false);
                messWebView.setFocusableInTouchMode(false);
                messWebView.loadData(message.getText(), "text/html", "UTF-8");
                view.findViewById(R.id.tv_view_more).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showHtmlviewPopup(message.getText());
                    }
                });
            } else if (message.isBotTable()) {
                view = layoutInflater.inflate(R.layout.item_bot_table, null);
                TableView tableView = view.findViewById(R.id.table_bot_message);
                TableAdapter adapter = new TableAdapter(context);
                tableView.setAdapter(adapter);
                tableView.setTableViewListener(new TableListener(tableView));
                List<BotColumnHeader> header = new ArrayList<>();
                List<List<BotCell>> content = new ArrayList<>();
                String [][] tableContent;
                try {
                    JSONArray jsona = new JSONArray(message.getText());
                    tableContent = new String[jsona.length()][jsona.getJSONArray(0).length()];
                    for(int k=0; k<jsona.getJSONArray(0).length(); k++){
                        header.add(new BotColumnHeader(jsona.getJSONArray(0).getString(k)));
                    }
                    for (int j=1; j <jsona.length(); j++){
                        List<BotCell> tmp = new ArrayList<>();
                        JSONArray jsonsub = jsona.getJSONArray(j);
                        for(int k=0; k<jsonsub.length(); k++){
                            tmp.add(new BotCell(""+(j-1)+"+"+k,jsonsub.getString(k)));
                        }
                        content.add(tmp);
                    }

//                    tableView.setDataAdapter(new SimpleTableDataAdapter(context, tableContent));
                    view.findViewById(R.id.tv_view_more).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showTablePopup(header, content);
                        }
                    });
                    adapter.setAllItems(header, null, content);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                view = layoutInflater.inflate(R.layout.item_bot_message, null);
                TextView messTextView = view.findViewById(R.id.tv_bot_message);
                messTextView.setText(message.getText());
            }
        } else {
            view = layoutInflater.inflate(R.layout.item_user_message, null);
            TextView messTextView = view.findViewById(R.id.tv_user_message);
            messTextView.setText(message.getText());
        }
        return view;
    }

    private void showHtmlviewPopup(String mes) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_htmlview_full, null, false);
        ((WebView) view.findViewById(R.id.wv_full_screen_bot_message)).loadData(mes, "text/html", "UTF-8");
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
    private void showTablePopup(List<BotColumnHeader> header, List<List<BotCell>> content) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_bot_table_full, null, false);
        TableAdapter adapter = new TableAdapter(context);
        ((TableView) view.findViewById(R.id.table_bot_message)).setAdapter(adapter);
        ((TableView) view.findViewById(R.id.table_bot_message)).setTableViewListener(new TableListener(view.findViewById(R.id.table_bot_message)));
        adapter.setAllItems(header, null, content);


        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
}
