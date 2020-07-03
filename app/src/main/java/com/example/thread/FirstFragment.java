package com.example.thread;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirstFragment extends Fragment {
    private static final String TAG = "FirstFragment";
    public static final String MY_PREF = "myPrefs";
    public static final String MY_WIN = "winNumber";
    public static final String MY_INTERVAL = "intervalNumber";

    SharedPreferences sp;

    private TextView txt_number1, txt_number2, txt_number3;
    private Thread thread1, thread2, thread3;
    private Random myRandom;
    private boolean condition1, condition2, condition3 = false;
    public int state;
    private double getInterval;
    private int num1, num2, num3;
    private int winVal, intervalVal;
    private List<NumberSpin> saveNumberData = new ArrayList<>();

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myRandom = new Random();
        txt_number1 = getActivity().findViewById(R.id.number1);
        txt_number2 = getActivity().findViewById(R.id.number2);
        txt_number3 = getActivity().findViewById(R.id.number3);

        sp = getActivity().getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        if (sp.contains(MY_INTERVAL)){
            intervalVal = sp.getInt(MY_INTERVAL, 0);
        }
        if (sp.contains(MY_WIN)){
            winVal = sp.getInt(MY_WIN, 0);
        }

        Button start = getActivity().findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 0;
                //intervalVal = 1000;
                //winVal= 7;

                condition1 = true;
                condition2 = true;
                condition3 = true;

                if(thread1 == null || thread1.getState() == Thread.State.TERMINATED) {
                    Runnable runnable1 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (condition1) {
                                    num1 = myRandom.nextInt(10);
                                    Thread.sleep(intervalVal);

                                    txt_number1.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            txt_number1.setText(String.valueOf(num1));
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    thread1 = new Thread(runnable1);
                    thread1.start();
                }

                if(thread2 == null || thread2.getState() == Thread.State.TERMINATED) {
                    Runnable runnable1 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (condition2) {
                                    num2 = myRandom.nextInt(10);
                                    Thread.sleep(intervalVal);

                                    txt_number2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            txt_number2.setText(String.valueOf(num2));
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    thread2 = new Thread(runnable1);
                    thread2.start();
                }

                if(thread3 == null || thread3.getState() == Thread.State.TERMINATED) {
                    Runnable runnable1 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (condition3) {
                                    num3 = myRandom.nextInt(10);
                                    Thread.sleep(intervalVal);

                                    txt_number3.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            txt_number3.setText(String.valueOf(num3));
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    thread3 = new Thread(runnable1);
                    thread3.start();
                }
            }
        });

        Button stop = getActivity().findViewById(R.id.btn_stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0){
                    //Toast.makeText(getActivity(), "Speed" + intervalVal + winVal, Toast.LENGTH_SHORT).show();
                    condition1 = false;
                    thread1.interrupt();
                    state++;
                } else if (state == 1){
                    condition2 = false;
                    thread2.interrupt();
                    state++;
                } else if (state == 2){
                    condition3 = false;
                    thread3.interrupt();


                    ((MainActivity)getActivity()).insertItem(num1,num2,num3);
                    ((MainActivity)getActivity()).addSaveNumber();
                    //saveNumberData = ((MainActivity)getActivity()).getlistSavedNumber();
                    Log.d("dataaaaa", "cekValue : " + saveNumberData.size());

                    //cek win number dengan text random
                    if(num1 == winVal && num2 == winVal && num3 == winVal){
                        ((MainActivity)getActivity()).addNotification();
                        Toast.makeText(getActivity(), "You Win!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("checkfrag", "di OnResume1");
        saveNumberData = ((MainActivity)getActivity()).getlistSavedNumber();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("checkfrag", "di OnStart1");
        saveNumberData = ((MainActivity)getActivity()).getlistSavedNumber();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("checkfrag", "di onStop1");
        saveNumberData = ((MainActivity)getActivity()).getlistSavedNumber();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("checkfrag", "di onPause1");
        saveNumberData = ((MainActivity)getActivity()).getlistSavedNumber();
    }
}
