package com.example.camerascannerj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BarCodeListAdapter extends RecyclerView.Adapter<BarCodeListAdapter.BarCodeViewHolder> {

<<<<<<< HEAD
    private final ArrayList<String> barcodeList;
=======
    private ArrayList<String> barcodeList;
>>>>>>> a0834d422503c41039d475e056fcba055534e53b

    public BarCodeListAdapter(ArrayList<String> barcodeList){
        this.barcodeList= barcodeList;
    }

    @NonNull
    @Override
    public BarCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barcode, parent, false);
        return new BarCodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarCodeListAdapter.BarCodeViewHolder holder, int position) {
        String barcode = barcodeList.get(position);

        String[] parts = barcode.split(" - ");
        String link = parts[0];
        String date= parts[1];

        holder.bind(link,date);
    }

    @Override
    public int getItemCount() {
        return barcodeList.size();
    }

    public static class BarCodeViewHolder extends RecyclerView.ViewHolder{
        private TextView linkTxt;
        private TextView dateTimeTxt;
        public BarCodeViewHolder(@NonNull View itemview){
            super(itemview);

            linkTxt = itemview.findViewById(R.id.text_view_barcode);
            dateTimeTxt= itemview.findViewById(R.id.date_txt);
        }
        public void bind(String barcode, String dateTime){
            linkTxt.setText(barcode);
            dateTimeTxt.setText(dateTime);
        }
    }
}
