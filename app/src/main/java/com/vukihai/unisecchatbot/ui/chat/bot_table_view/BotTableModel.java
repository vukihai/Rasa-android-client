package com.vukihai.unisecchatbot.ui.chat.bot_table_view;

import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;

public class BotTableModel {

    private List<BotColumnHeader> columnHeader;
    private List<BotRowHeader> rowHeader;
    private List<List<BotCell>> cell;
    public int getColumnTextAlign(int column) {
        return Gravity.LEFT;
    }
    public int getCellItemViewType(int column) {
        return 0;
    }
    public BotTableModel(){
        createCellModelList();
        createColumnHeaderModelList();
//        creat
    }
    private List<BotColumnHeader> createColumnHeaderModelList() {
        List<BotColumnHeader> list = new ArrayList<>();
        list.add(new BotColumnHeader("tên trường"));
        list.add(new BotColumnHeader("tên ngành"));
        list.add(new BotColumnHeader("điểm chuẩn"));
        list.add(new BotColumnHeader("khối thi"));
        return list;
    }
    private List<List<BotCell>> createCellModelList() {
        List<List<BotCell>> lists = new ArrayList<>();
        for (int i = 0; i <5; i++) {
            List<BotCell> list = new ArrayList<>();
            list.add(new BotCell("1-" +i, "đại học công nghệ"));
            list.add(new BotCell("2-" +i, "công nghệ thông tin"));
            list.add(new BotCell("3-" +i, "25"));
            list.add(new BotCell("4-" +i, "A00"));
            lists.add(list);
        }
        return lists;
    }

    public List<BotColumnHeader> getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(List<BotColumnHeader> columnHeader) {
        this.columnHeader = columnHeader;
    }

    public List<BotRowHeader> getRowHeader() {
        return rowHeader;
    }

    public void setRowHeader(List<BotRowHeader> rowHeader) {
        this.rowHeader = rowHeader;
    }

    public List<List<BotCell>> getCell() {
        return cell;
    }

    public void setCell(List<List<BotCell>> cell) {
        this.cell = cell;
    }
}
