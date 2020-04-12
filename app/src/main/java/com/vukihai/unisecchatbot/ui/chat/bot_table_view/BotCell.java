package com.vukihai.unisecchatbot.ui.chat.bot_table_view;

import com.evrencoskun.tableview.sort.ISortableModel;

public class BotCell implements ISortableModel {
    private String mId;
    private Object mData;

    public BotCell(String pId, Object mData) {
        this.mId = pId;
        this.mData = mData;
    }

    public Object getData() {
        return mData;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public Object getContent() {
        return mData;
    }

}