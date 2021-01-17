package com.example.kelompokppbbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class bubbletea extends AppCompatActivity {

    Button kurang1, tambah1;
    TextView jumlah1, total1;
    int jml=1;
    int ttl=25000;
    databaseHelper BantuDb;
    Button proses;
    TextView namaproduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubbletea);

        BantuDb = new databaseHelper(this);
        namaproduk = (TextView)findViewById(R.id.xnamaproduk);
        proses = (Button)findViewById(R.id.tmblProses);

        kurang1=(Button)findViewById(R.id.kurang);
        tambah1=(Button)findViewById(R.id.tambah);
        jumlah1=(TextView)findViewById(R.id.jumlah);
        total1=(TextView)findViewById(R.id.total);

        kurang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jml--;
                ttl=ttl-25000;
                jumlah1.setText(" "+jml);
                total1.setText(" "+ttl);
            }
        });

        tambah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jml++;
                ttl=ttl+25000;
                jumlah1.setText(" "+jml);
                total1.setText(" "+ttl);
            }
        });

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = BantuDb.insertDataProduk(namaproduk.getText().toString(), jml, ttl);
                if (isInserted == true)
                    Toast.makeText(bubbletea.this,"Data Saved",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(bubbletea.this,"Data Not Saved",Toast.LENGTH_SHORT).show();
            }
        });
    }
}