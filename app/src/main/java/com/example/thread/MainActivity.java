package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private MyAdapter mAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SharedPreferences sharedPreferencesSavedNumber;
    private List<NumberSpin> savedNumberArrayList = new ArrayList<>();
    private Gson gson;
    private Double intervalVal;
    private int winVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        //viewPager.getAdapter().notifyDataSetChanged();

        getlistSavedNumber();
        setPagerAdapter();
        setTabLayout();
        sharedPreferencesSavedNumber = getSharedPreferences("savedNumberList", Context.MODE_PRIVATE);
        Log.d("dataSaved",""+savedNumberArrayList.size());
    }

    public void setPagerAdapter(){
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        getlistSavedNumber();
        viewPager.setAdapter(myFragmentPagerAdapter);
    }

    private void setTabLayout(){
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(getDrawable(R.drawable.ic_home_black_24dp));
        tabLayout.getTabAt(1).setIcon(getDrawable(R.drawable.ic_format_list_bulleted_black_24dp));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Intent i = new Intent(this, SettingActivity.class);
                startActivity(i);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void addNotification(){
        NotificationManager nm = (NotificationManager)this.getSystemService(this.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channell_id_01";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            @SuppressLint("WrongConstant") NotificationChannel nc = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            nc.setDescription("Channel Deskription");
            nc.enableLights(true);
            nc.setLightColor(Color.BLUE);
            nm.createNotificationChannel(nc);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_notifications);
        builder.setContentTitle("You Win");
        builder.setContentText("You win because you have 3 same number");

        nm.notify(1, builder.build());
    }

    public void addSaveNumber() {
        SharedPreferences.Editor editor = sharedPreferencesSavedNumber.edit();
        Gson gson = new Gson();
        String json = gson.toJson(savedNumberArrayList);
        editor.putString("listNumbers", json);
        editor.apply();
    }

    public void insertItem(int numberOneSaved, int numberTwoSaved, int numberThreeSaved){
        savedNumberArrayList.add(new NumberSpin(numberOneSaved, numberTwoSaved, numberThreeSaved));
        //mAdapter.notifyItemInserted(savedNumberArrayList.size());
        //this.addSaveNumber();
        Log.d("dataaaaaa", "BerhasilInsert :" + savedNumberArrayList.size());
    }

    public List<NumberSpin> getlistSavedNumber(){
        SharedPreferences pref = getSharedPreferences("savedNumberList", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<NumberSpin>>(){}.getType();
        String json = pref.getString("listNumbers", null);
        savedNumberArrayList = gson.fromJson(json, type);
        return savedNumberArrayList;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("dataaaaaa", "onStart Activity :" + savedNumberArrayList.size());
        getlistSavedNumber();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("dataaaaaa", "onStop Activity :" + savedNumberArrayList.size());
        getlistSavedNumber();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("dataaaaaa", "onResumeActivity :" + savedNumberArrayList.size());
        getlistSavedNumber();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("dataaaaaa", "onPause Activity :" + savedNumberArrayList.size());
        getlistSavedNumber();
    }
}
