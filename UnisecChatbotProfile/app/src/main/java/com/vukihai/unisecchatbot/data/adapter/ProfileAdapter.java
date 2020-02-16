package com.vukihai.unisecchatbot.data.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vukihai.unisecchatbot.R;
import com.vukihai.unisecchatbot.data.model.FunctionProfile;

import java.util.List;

public class ProfileAdapter extends BaseAdapter {

    private List<FunctionProfile> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ProfileAdapter(Context aContext,  List<FunctionProfile> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_profile_listview, null);
            holder = new ViewHolder();
            holder.flagView = (ImageView) convertView.findViewById(R.id.img_icon_profile);
            holder.FunctionProfileNameView = (TextView) convertView.findViewById(R.id.tv_name_profile);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FunctionProfile FunctionProfile = this.listData.get(position);
        holder.FunctionProfileNameView.setText(FunctionProfile.getnameFunction());

//        int imageId = this.getMipmapResIdByName(FunctionProfile.geticonFunction());
        int imageId = this.getMipmapResIdByName(FunctionProfile.geticonFunction());

        holder.flagView.setImageResource(imageId);

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView flagView;
        TextView FunctionProfileNameView;
        TextView populationView;
    }

}
