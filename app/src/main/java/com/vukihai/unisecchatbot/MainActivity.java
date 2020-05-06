package com.vukihai.unisecchatbot;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vukihai.unisecchatbot.ui.chat.ChatFragment;
import com.vukihai.unisecchatbot.ui.settings.SettingsActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQ_CODE_SPEECH_INPUT = 1;
    private Intent settingIntent;
    private LinearLayout chatInputLinearLayout;
    private ImageView profileImageView, chatImageView, statisticImageView;
    private HorizontalScrollView suggestHorizontalScrollView;
    private ConstraintLayout bottomNavConstraintLayout;
    private EditText chatMessageEditText;
    private ImageButton sendButton, voiceButton;
    private TextView networkStatusTextView;
//    private BottomSheetBehavior bottomSheetBehavior;


    //
//    TextView textViewBottomSheetState;
//    Button toggleBottomSheet;
    //

    private static final int NUM_PAGES = 3;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
//    private Fragment[] fragments;

    private boolean doubleBackPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#000000\">" + getString(R.string.app_name) + "</font>")));
        getSupportActionBar().setSubtitle((Html.fromHtml("<font color=\"#718792\">" + getString(R.string.app_name_subtitle) + "</font>")));
        bottomNavConstraintLayout = findViewById(R.id.linear_bottom_nav);
        chatInputLinearLayout = findViewById(R.id.linear_chat_input);
        profileImageView = findViewById(R.id.img_profile);
        chatImageView = findViewById(R.id.img_chat);
        statisticImageView = findViewById(R.id.img_statistics);
        suggestHorizontalScrollView = findViewById(R.id.scroll_suggest_button);
        chatMessageEditText = findViewById(R.id.edt_input_message);
        sendButton = findViewById(R.id.btn_send);
        voiceButton = findViewById(R.id.btn_voice);
        networkStatusTextView = findViewById(R.id.tv_network_status);
        // instance.
//        fragments = new Fragment[NUM_PAGES];
//        fragments[0] = new ProfileFragment();
//        fragments[1] = new ChatFragment();
//        fragments[2] = new StatisticsFragment();

//        mViewPager = findViewById(R.id.pager_main);
//        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        mViewPager.setAdapter(mPagerAdapter);
//        mViewPager.setPageTransformer(true, new MyPagerTransformer());
//        mViewPager.setOffscreenPageLimit(3);
//        mViewPager.setCurrentItem(1);

        chatMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() != 0){
                    sendButton.setVisibility(View.VISIBLE);
                    voiceButton.setVisibility(View.GONE);
                } else {
                    sendButton.setVisibility(View.GONE);
                    voiceButton.setVisibility(View.VISIBLE);
                }
            }
        });
//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_profile, R.id.navigation_statistics, R.id.navigation_chat)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
    }


    /**
     * adding setting button on support action bar.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.primary_actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * handle setting button click.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.btn_setting) {
            if (settingIntent == null) settingIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingIntent);
        }
        if (id == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }
    public void updateNetworkStatus(String status, int visiblility) {
        networkStatusTextView.setText(status);
        networkStatusTextView.setVisibility(visiblility);
    }
    /**
     * handle backpressed: press 2 time to exit.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!doubleBackPressedOnce) {
            doubleBackPressedOnce = true;
            Toast.makeText(this, getString(R.string.main_activity_double_back_press_once), Toast.LENGTH_SHORT);
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_chat:
                mViewPager.setCurrentItem(1, true);
                break;
            case R.id.img_profile:
                if(mViewPager.getCurrentItem() == 2)
                    mViewPager.setCurrentItem(0, false);
                else
                    mViewPager.setCurrentItem(0,true);
                break;
            case R.id.img_statistics:
                if(mViewPager.getCurrentItem() == 0)
                    mViewPager.setCurrentItem(2, false);
                else
                    mViewPager.setCurrentItem(2,true);
                break;
            case R.id.btn_send:
                ((ChatFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chat)).send(chatMessageEditText.getText().toString());
                chatMessageEditText.setText("");
                break;
            case R.id.btn_voice:
                promptSpeechInput();
                break;

        }
    }
    public void sendMessage(String message) {
        if(message.length() != 0)
            ((ChatFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chat)).send(message);
    }
//    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ((ChatFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chat)).send(result.get(0));
                }
                break;
            }

        }
    }
//    private class SlidePagerAdapter extends FragmentStatePagerAdapter {
//
//        public SlidePagerAdapter(@NonNull FragmentManager fm, int behavior) {
//            super(fm, behavior);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragments[position];
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//    }


    /**
     * animation for bottom navigation.
     */
//    private class MyPagerTransformer implements ViewPager.PageTransformer {
//
//        @Override
//        public void transformPage(@NonNull View page, float position) {
//            float pageHeight = getResources().getDimension(R.dimen.bottom_navigation_height);
//            int pageWidth = page.getWidth();
//            if (mViewPager.getCurrentItem() == 0) {
//                if (position >= -1 && position <= 0) {
//                    suggestHorizontalScrollView.setTranslationY(position * pageHeight);
//                    suggestHorizontalScrollView.setAlpha(-position*3-2);
//                    bottomNavConstraintLayout.setTranslationY(position * pageHeight);
//                    chatInputLinearLayout.setTranslationY(position * pageHeight);
//                    chatImageView.setTranslationY(position * pageHeight);
//                    profileImageView.setTranslationX(position * (pageWidth/10));
//                    statisticImageView.setTranslationX(-position * (pageWidth/10));
//
//                }
//            }
//            if (mViewPager.getCurrentItem() == 1) {
//                if (position < -1) {
//                    page.setAlpha(1);
//                } else if (position <= 0) {
//                    suggestHorizontalScrollView.setTranslationY(position * pageHeight);
//                    suggestHorizontalScrollView.setAlpha(-position*3-2);
//                    bottomNavConstraintLayout.setTranslationY(position *pageHeight);
//                    chatInputLinearLayout.setTranslationY(position *pageHeight );
//                    chatImageView.setTranslationY(position * pageHeight);
//                    profileImageView.setTranslationX(position * (pageWidth/10));
//                    statisticImageView.setTranslationX(-position * (pageWidth/10));
//                } else if (position <= 1) {
//                    suggestHorizontalScrollView.setTranslationY(-position * pageHeight);
//                    suggestHorizontalScrollView.setAlpha(position*3-2);
//                    bottomNavConstraintLayout.setTranslationY(-position* pageHeight);
//                    chatInputLinearLayout.setTranslationY(-position* pageHeight);
//                    chatImageView.setTranslationY(-position* pageHeight);
//                    profileImageView.setTranslationX(-position * (pageWidth/10));
//                    statisticImageView.setTranslationX(position * (pageWidth/10));
//
//                } else {
//                    page.setAlpha(1);
//                }
//            }
//
//            if (mViewPager.getCurrentItem() == 2) {
//                if (position >= 0 && position <= 1) {
//                    suggestHorizontalScrollView.setTranslationY(-position * pageHeight);
//                    suggestHorizontalScrollView.setAlpha(position*3-2);
//                    bottomNavConstraintLayout.setTranslationY(-position * pageHeight);
//                    chatInputLinearLayout.setTranslationY(-position * pageHeight);
//                    chatImageView.setTranslationY(-position * pageHeight);
//                    profileImageView.setTranslationX(-position * (pageWidth/10));
//                    statisticImageView.setTranslationX(position * (pageWidth/10));
//
//                }
//            }
//
//        }
//    }
}
