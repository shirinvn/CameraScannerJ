package com.example.camerascannerj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScannerActivity extends AppCompatActivity {

    private List<String> scannedBarcodes= new ArrayList<>();
    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
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
                    String linkWithDateTime = scannedData + "- Date/Time: " + dateTime;
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


    private void returnResultToPreviousActivity(String result){
        Intent intent = new Intent();
        intent.putExtra(RESULT_KEY,result);
        setResult(RESULT_OK,intent);
        finish();
    }

    private String getCurrentDateTime(){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    private void addScannedBarcode(String barcode){
        scannedBarcodes.add(barcode);
    }

    private void startNewActivityWithScannedBarCodes(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}