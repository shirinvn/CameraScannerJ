package com.example.camerascannerj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<String> scannedBarcodesList= new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        scannedBarcodesList= getIntent().getStringArrayListExtra("scannedBarcodesList");
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (scannedBarcodesList != null){
            BarCodeListAdapter adapter= new BarCodeListAdapter(scannedBarcodesList);
            recyclerView.setAdapter(adapter);
        }else {
            Log.e("Scanned Barcode", "is empty");

        }

    }
}