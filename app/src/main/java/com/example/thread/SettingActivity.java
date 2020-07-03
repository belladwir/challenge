package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    private EditText intervalNum, winNum;
    Button button;

    public static final String MY_PREF = "myPrefs";
    public static final String MY_WIN = "winNumber";
    public static final String MY_INTERVAL = "intervalNumber";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        intervalNum = (EditText)findViewById(R.id.edittextInterval);
        winNum = (EditText)findViewById(R.id.edittextWin);
        button = (Button)findViewById(R.id.btnSave);
        sp = getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);

        if(sp.contains(MY_INTERVAL)){
            intervalNum.setText(String.valueOf(sp.getInt(MY_INTERVAL, 0)));
        }
        if (sp.contains(MY_WIN)){
            winNum.setText(String.valueOf(sp.getInt(MY_WIN,0)));
        }
    }

    public void saveSetting(View view) {
        int intervalValue = Integer.parseInt(intervalNum.getText().toString());
        int winValue = Integer.parseInt(winNum.getText().toString());

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(MY_INTERVAL, intervalValue);
        editor.putInt(MY_WIN, winValue);
        editor.commit();

        Toast.makeText(this, "Saved! Your Win Number is : " + winValue, Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
