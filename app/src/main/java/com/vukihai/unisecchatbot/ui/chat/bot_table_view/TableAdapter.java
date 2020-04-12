package com.vukihai.unisecchatbot.ui.chat.bot_table_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.vukihai.unisecchatbot.R;

public class TableAdapter extends AbstractTableAdapter<BotColumnHeader, BotRowHeader, BotCell> {

    private BotTableModel botTableModel;
    private Context mContext;
    public TableAdapter(Context p_jContext) {
        mContext = p_jContext;
        this.botTableModel = new BotTableModel();
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_cell_layout,
                parent, false);
        return new CellViewHolder(layout);
    }
    @Override
    public void onBindCellViewHolder(@NonNull AbstractViewHolder holder, @Nullable BotCell cellItemModel, int columnPosition, int rowPosition) {
        if (holder instanceof CellViewHolder) {
            ((CellViewHolder) holder).setCellModel(cellItemModel, columnPosition);
        }
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout
                .tableview_column_header_layout, parent, false);
        return new ColumnHeaderViewHolder(layout, getTableView());
    }

//    @Override
//    public AbstractSorterViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
//        View layout = LayoutInflater.from(mContext).inflate(R.layout
//                .tableview_column_header_layout, parent, false);
//
//        return new ColumnHeaderViewHolder(layout, getTableView());
//    }

    @Override
    public void onBindColumnHeaderViewHolder(@NonNull AbstractViewHolder holder, @Nullable BotColumnHeader columnHeaderItemModel, int columnPosition) {

        // Get the holder to update cell item text
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;

        columnHeaderViewHolder.setColumnHeaderModel(columnHeaderItemModel, columnPosition);
    }


    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_row_header_layout,
                parent, false);

        // Create a Row Header ViewHolder
        return new RowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(@NonNull AbstractViewHolder holder, @Nullable BotRowHeader rowHeaderItemModel, int rowPosition) {

        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText(rowHeaderItemModel.getData());
    }

    @NonNull
    @Override
    public View onCreateCornerView(@NonNull ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.tableview_corner_layout, null, false);
    }
    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return botTableModel.getCellItemViewType(position);
    }


    /**
     * This method is not a generic Adapter method. It helps to generate lists from single user
     * list for this adapter.
     */
//    public void setUserList(List<User> userList) {
//        // Generate the lists that are used to TableViewAdapter
//        myTableViewModel.generateListForTableView(userList);
//
//        // Now we got what we need to show on TableView.
//        setAllItems(myTableViewModel.getColumHeaderModeList(), myTableViewModel
//                .getRowHeaderModelList(), myTableViewModel.getCellModelList());
//    }

}