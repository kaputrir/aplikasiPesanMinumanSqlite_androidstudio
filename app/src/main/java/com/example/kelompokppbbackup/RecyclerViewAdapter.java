package com.example.kelompokppbbackup;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Integer> kodePesanan = new ArrayList<>();
    private ArrayList<String> namaPesanan = new ArrayList<>();
    private ArrayList<Integer> jumlahPesanan = new ArrayList<>();
    private ArrayList<Integer> hargaPesanan = new ArrayList<>();
    private Context context;


    public RecyclerViewAdapter(ArrayList<Integer> kodePesanan, ArrayList<String> namaPesanan, ArrayList<Integer> jumlahPesanan, ArrayList<Integer> hargaPesanan, Context context) {
        this.kodePesanan = kodePesanan;
        this.namaPesanan = namaPesanan;
        this.jumlahPesanan = jumlahPesanan;
        this.hargaPesanan = hargaPesanan;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desain_layout_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nama_pesanan.setText(namaPesanan.get(position));
        holder.jumlah_pesanan.setText(String.valueOf(jumlahPesanan.get(position)));
        holder.harga_pesanan.setText(String.valueOf(hargaPesanan.get(position)));
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper myDB = new databaseHelper(context);
                myDB.deleteOneRow(namaPesanan.get(position));
                Intent i_1 = new Intent(context, pesanan.class);
                context.startActivity(i_1);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return namaPesanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nama_pesanan;
        TextView jumlah_pesanan;
        TextView harga_pesanan;
        Button delete_btn;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_pesanan = itemView.findViewById(R.id.nama_pesanan);
            jumlah_pesanan = itemView.findViewById(R.id.jumlah_pesanan);
            harga_pesanan = itemView.findViewById(R.id.harga_pesanan);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            delete_btn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
