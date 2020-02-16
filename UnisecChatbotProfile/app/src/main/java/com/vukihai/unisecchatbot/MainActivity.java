package com.vukihai.unisecchatbot;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vukihai.unisecchatbot.ui.chat.ChatFragment;
import com.vukihai.unisecchatbot.ui.profile.ProfileFragment;
import com.vukihai.unisecchatbot.ui.settings.SettingsActivity;
import com.vukihai.unisecchatbot.ui.statistics.StatisticsFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent settingIntent;
    private LinearLayout chatInputLinearLayout;
    private ImageView profileImageView, chatImageView, statisticImageView;
    private HorizontalScrollView suggestHorizontalScrollView;
    private ConstraintLayout bottomNavConstraintLayout;

    private static final int NUM_PAGES = 3;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private Fragment[] fragments;

    private boolean doubleBackPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavConstraintLayout = findViewById(R.id.linear_bottom_nav);
        chatInputLinearLayout = findViewById(R.id.linear_chat_input);
        profileImageView = findViewById(R.id.img_profile);
        chatImageView = findViewById(R.id.img_chat);
        statisticImageView = findViewById(R.id.img_statistics);
        suggestHorizontalScrollView = findViewById(R.id.scroll_suggest_button);

        // instance.
        fragments = new Fragment[NUM_PAGES];
        fragments[0] = new ProfileFragment();
        fragments[1] = new ChatFragment();
        fragments[2] = new StatisticsFragment();

        mViewPager = findViewById(R.id.pager_main);
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true, new MyPagerTransformer());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(1);

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
        return super.onOptionsItemSelected(item);
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
        Log.d("vukihai", "onclick " + view.getId());
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
        }
    }


    private class SlidePagerAdapter extends FragmentStatePagerAdapter {

        public SlidePagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


    /**
     * animation for bottom navigation.
     */
    private class MyPagerTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(@NonNull View page, float position) {
            float pageHeight = getResources().getDimension(R.dimen.bottom_navigation_height);
            int pageWidth = page.getWidth();
            if (mViewPager.getCurrentItem() == 0) {
                if (position >= -1 && position <= 0) {
                    suggestHorizontalScrollView.setTranslationY(position * pageHeight);
                    suggestHorizontalScrollView.setAlpha(-position*3-2);
                    bottomNavConstraintLayout.setTranslationY(position * pageHeight);
                    chatInputLinearLayout.setTranslationY(position * pageHeight);
                    chatImageView.setTranslationY(position * pageHeight);
                    profileImageView.setTranslationX(position * (pageWidth/10));
                    statisticImageView.setTranslationX(-position * (pageWidth/10));

                }
            }
            if (mViewPager.getCurrentItem() == 1) {
                if (position < -1) {
                    page.setAlpha(1);
                } else if (position <= 0) {
                    suggestHorizontalScrollView.setTranslationY(position * pageHeight);
                    suggestHorizontalScrollView.setAlpha(-position*3-2);
                    bottomNavConstraintLayout.setTranslationY(position *pageHeight);
                    chatInputLinearLayout.setTranslationY(position *pageHeight );
                    chatImageView.setTranslationY(position * pageHeight);
                    profileImageView.setTranslationX(position * (pageWidth/10));
                    statisticImageView.setTranslationX(-position * (pageWidth/10));
                } else if (position <= 1) {
                    suggestHorizontalScrollView.setTranslationY(-position * pageHeight);
                    suggestHorizontalScrollView.setAlpha(position*3-2);
                    bottomNavConstraintLayout.setTranslationY(-position* pageHeight);
                    chatInputLinearLayout.setTranslationY(-position* pageHeight);
                    chatImageView.setTranslationY(-position* pageHeight);
                    profileImageView.setTranslationX(-position * (pageWidth/10));
                    statisticImageView.setTranslationX(position * (pageWidth/10));

                } else {
                    page.setAlpha(1);
                }
            }

            if (mViewPager.getCurrentItem() == 2) {
                if (position >= 0 && position <= 1) {
                    suggestHorizontalScrollView.setTranslationY(-position * pageHeight);
                    suggestHorizontalScrollView.setAlpha(position*3-2);
                    bottomNavConstraintLayout.setTranslationY(-position * pageHeight);
                    chatInputLinearLayout.setTranslationY(-position * pageHeight);
                    chatImageView.setTranslationY(-position * pageHeight);
                    profileImageView.setTranslationX(-position * (pageWidth/10));
                    statisticImageView.setTranslationX(position * (pageWidth/10));

                }
            }

        }
    }
}
