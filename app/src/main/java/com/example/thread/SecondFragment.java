package com.example.thread;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private static final String TAG = "SecondFragment";
    private RecyclerView recyclerView;
    private MyAdapter savedNumberAdapter;
    private List<NumberSpin> savedNumbers = new ArrayList<>();

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.recyclerView);

        // Fetch data List from MainActivity
        savedNumbers = ((MainActivity)getActivity()).getlistSavedNumber();
        if(savedNumbers != null) {
            Collections.reverse(savedNumbers);
            savedNumberAdapter = new MyAdapter(getContext(), (ArrayList<NumberSpin>) savedNumbers);
            recyclerView.setAdapter(savedNumberAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            savedNumberAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(getFragmentManager() != null){
                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("checkfrag", "di OnResume2");
        savedNumbers = ((MainActivity)getActivity()).getlistSavedNumber();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("checkfrag", "di OnStart2");
        savedNumbers = ((MainActivity)getActivity()).getlistSavedNumber();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("checkfrag", "di onStop2");
        savedNumbers = ((MainActivity)getActivity()).getlistSavedNumber();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("checkfrag", "di onPause2");
        savedNumbers = ((MainActivity)getActivity()).getlistSavedNumber();
    }

}
