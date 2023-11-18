package com.example.camerascannerj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import saman.zamani.persiandate.PersianDate;

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
//        Intent intent= getIntent();
//        String scannedBarCode = intent.getStringExtra("scannedBarCode");
//        if (scannedBarCode != null){
//            String[] parts = scannedBarCode.split(" - ");
//            String link = parts[0];
//            String dateTime = parts[1];
//
//            TextView linkTextView = findViewById(R.id.text_view_barcode);
//            TextView dateTimeTextView= findViewById(R.id.date_txt);
//            linkTextView.setText(link);
//            dateTimeTextView.setText(dateTime);

//            ArrayList<String> scannedData= new ArrayList<>();
//            scannedData.add(scannedBarCode);
//            BarCodeListAdapter adapter= new BarCodeListAdapter(scannedData);
//            recyclerView.setAdapter(adapter);
        }

   //     showPersianScanDateTime();

    }


//    private void showPersianScanDateTime(){
//        PersianDate persianDate= new PersianDate();
//        long timeInMilliseconds = persianDate.getTime();
//        Date scanDateTime= new Date(timeInMilliseconds);
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
//        String persianScanDateTime =sdf.format(scanDateTime);
//
//
//        Log.e("Persian Scan DateTime", persianScanDateTime);


 //   }
//}