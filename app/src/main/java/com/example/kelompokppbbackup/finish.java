package com.example.kelompokppbbackup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class finish extends AppCompatActivity {

    int hargaTotal;
    TextView name;
    TextView address;
    TextView total;
    databaseHelper BantuDb;

    Button tblOrder;

    ArrayList<Integer> kodePesanan = new ArrayList<>();
    ArrayList<String> namaPesanan = new ArrayList<>();
    ArrayList<Integer> jumlahPesanan = new ArrayList<>();
    ArrayList<Integer> hargaPesanan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        BantuDb = new databaseHelper(this);
        name = (TextView) findViewById(R.id.xname);
        address = (TextView) findViewById(R.id.xaddress);
        total = (TextView)findViewById(R.id.hargaTotal);

        tblOrder = findViewById(R.id.tmblOrder);

        viewAll();
        getDataFromDatabase();
        total.setText("TOTAL: Rp " + hargaTotal);

        tblOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = BantuDb.updateData("0");
                if (result==true){
                    Intent intent = new Intent(finish.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void prosesRecyclerViewAdapter(ArrayList<Integer> kodePesanan, ArrayList<String> namaPesanan, ArrayList<Integer> jumlahPesanan, ArrayList<Integer> hargaPesanan){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter2 adapter = new RecyclerViewAdapter2(kodePesanan, namaPesanan, jumlahPesanan, hargaPesanan, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataFromDatabase(){
        Cursor res = BantuDb.getAllDataProduk();

        if(res.getCount()==0){
            Toast.makeText(this, "Input Your Order", Toast.LENGTH_SHORT).show();
            return;
        }
        hargaTotal = 0;
        while(res.moveToNext()){
            //listviewku.add(cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getInt(2)+" "+cursor.getInt(3));
            kodePesanan.add(res.getInt(0));
            namaPesanan.add(res.getString(1));
            jumlahPesanan.add(res.getInt(2));
            hargaPesanan.add(res.getInt(3));
            hargaTotal = hargaTotal + res.getInt(3);

            prosesRecyclerViewAdapter(kodePesanan, namaPesanan, jumlahPesanan, hargaPesanan);
        }
    }

    public void viewAll(){
        Cursor res = BantuDb.getAllData();
        if(res.getCount() == 0){
//                    showMessage("Error", "Nothing Found");
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            return;
        }

        if (res.moveToLast()){
            name.setText(res.getString(1));
            address.setText(res.getString(2));
        }
    }
}