package com.example.camerascannerj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ScannerActivity extends AppCompatActivity {

    private List<String> scannedBarcodes= new ArrayList<>();
    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    HistoryActivity historyActivity;
    public static final String RESULT_KEY="resultKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        init();
    }

    private  void init(){
        codeScannerView= findViewById(R.id.code_scanner_view);
        codeScanner = new CodeScanner(this,codeScannerView);

        codeScanner.setDecodeCallback(decodeCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();

    }



    DecodeCallback decodeCallback = new DecodeCallback() {
        @Override
        public void onDecoded(@NonNull Result result) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    String scannedData = result.getText();

                    String dateTime = getCurrentDateTime();
                    String linkWithDateTime = scannedData + " - " + dateTime;
                    new AlertDialog.Builder(ScannerActivity.this)

                            .setTitle("توجه")
                            .setMessage("آیا میخاهید از اطلاعات این کد استفاده کنید؟")

                            .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent();

                                    intent.putExtra(RESULT_KEY, result.getText());
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("خیر تلاش مجدد", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    codeScanner.startPreview();

                                }
                            })
                            .show();

                    returnResultToPreviousActivity(linkWithDateTime);
                }
            });

        }
    };


    @SuppressLint("MutatingSharedPrefs")
    private void returnResultToPreviousActivity(String result) {
        // اضافه کردن اطلاعات به SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> scannedBarcodesSet = sharedPreferences.getStringSet("scannedBarcodes", new HashSet<>());
        scannedBarcodesSet.add(result);
        editor.putStringSet("scannedBarcodes", scannedBarcodesSet);
        editor.apply();

    }






    private String getCurrentDateTime(){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }


}