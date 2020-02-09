package com.vukihai.unisecchatbot.ui.statistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StatisticsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StatisticsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Biểu đồ tỉ lệ tra cứu của các trường đại học");
    }

    public LiveData<String> getText() {
        return mText;
    }
}