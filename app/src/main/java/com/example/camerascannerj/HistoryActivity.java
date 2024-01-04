package com.example.camerascannerj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import saman.zamani.persiandate.PersianDate;


public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> scannedBarcodesList;
    BarCodeListAdapter adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadScannedBarcodes();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        adapter = new BarCodeListAdapter(scannedBarcodesList);
        recyclerView.setAdapter(adapter);
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        loadScannedBarcodes();
        adapter.notifyDataSetChanged();
    }


    @SuppressLint("NotifyDataSetChanged")
    private void loadScannedBarcodes() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Set<String> scannedBarcodesSet = sharedPreferences.getStringSet("scannedBarcodes", new HashSet<>());
        scannedBarcodesList = new ArrayList<>(scannedBarcodesSet);



    //    sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
/*        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> scannedBarcodesSet1 = new HashSet<>(scannedBarcodesList);
        editor.putStringSet("scannedBarcodes", scannedBarcodesSet1);
        editor.apply();*/


    }

}




