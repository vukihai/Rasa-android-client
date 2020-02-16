package com.vukihai.unisecchatbot.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vukihai.unisecchatbot.MainActivity;
import com.vukihai.unisecchatbot.R;
import com.vukihai.unisecchatbot.SupportActivity;
import com.vukihai.unisecchatbot.data.adapter.ProfileAdapter;
import com.vukihai.unisecchatbot.data.model.FunctionProfile;

import java.util.List;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
//    private ProfileAdapter profileAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        List<FunctionProfile> image_details = getListData();
        final ListView listView = (ListView) root.findViewById(R.id.listView);
        listView.setAdapter(new ProfileAdapter(getContext(),image_details));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                FunctionProfile functionProfile = (FunctionProfile) o;
                Toast.makeText(getContext(), functionProfile.getnameFunction() , Toast.LENGTH_LONG).show();
//                if(functionProfile.geticonFunction().equals("ic_help")) {
//                    Intent intent = new Intent(getActivity(), SupportActivity.class);
//                    startActivity(intent);
//                }
            }
        });
        return root;
    }

    private  List<FunctionProfile> getListData() {
        List<FunctionProfile> list = new ArrayList<FunctionProfile>();
        FunctionProfile dangXuat = new FunctionProfile("ic_close", "Đăng xuất");
        FunctionProfile lienHe = new FunctionProfile("ic_help", "Liên hệ");

        list.add(dangXuat);
        list.add(lienHe);

        return list;
    }
}