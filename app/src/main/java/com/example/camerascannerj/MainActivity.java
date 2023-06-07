package com.example.camerascannerj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cardView;
    Button openCamera;
    TextView instructiontext,detaillinktext;
    public static final int PERMISSION_CODE=100;
    public static final int AC_PERMISSION_CODE=200;

    String[] permission= new String[]{Manifest.permission.CAMERA};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void  init(){
        cardView=findViewById(R.id.cardview);
        openCamera= findViewById(R.id.open_camera);
        instructiontext=findViewById(R.id.instructiontext);
        detaillinktext=findViewById(R.id.detail_linktext);
        openCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.open_camera:

                if (ActivityCompat.checkSelfPermission(this,permission[0]) == PackageManager.PERMISSION_GRANTED){

                    OpenCamera();
                }else
                {
                    ActivityCompat.requestPermissions(this,permission,PERMISSION_CODE);
                }

                break;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                OpenCamera();

            }else {
                ActivityCompat.requestPermissions(this,permission,PERMISSION_CODE);

            }
        }
    }

    private void OpenCamera(){
        Intent intent= new Intent(MainActivity.this,ScannerActivity.class);
        startActivityForResult(intent,AC_PERMISSION_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AC_PERMISSION_CODE && resultCode == RESULT_OK){
            cardView.setVisibility(View.VISIBLE);
            instructiontext.setVisibility(View.VISIBLE);
            assert data != null;
            detaillinktext.setText(data.getStringExtra(ScannerActivity.RESULT_KEY));
        }
    }


}