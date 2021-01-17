package com.example.kelompokppbbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class biodata extends AppCompatActivity {

    EditText xnama;
    EditText xalamat;
    Button tmblMasuk;
    databaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        BantuDb = new databaseHelper(this);
        xnama = (EditText)findViewById(R.id.xnama);
        xalamat = (EditText)findViewById(R.id.xalamat);
        tmblMasuk = (Button)findViewById(R.id.tmblMasuk);


        tmblMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(xnama.getText().toString().equals("") || xalamat.getText().toString().equals("")){
                    Toast.makeText(biodata.this,"Fill All Data",Toast.LENGTH_LONG).show();
                } else {
                    boolean isInserted = BantuDb.insertData(xnama.getText().toString(), xalamat.getText().toString());
                    if (isInserted == true){
                        Intent intent=new Intent(biodata.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(biodata.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();
                    }
                }

//                boolean isInserted = BantuDb.insertData(xnama.getText().toString(), xalamat.getText().toString());
//                if (isInserted == true){
//                    Intent intent=new Intent(biodata.this,MainActivity.class);
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(biodata.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();
//                }

            }
        });
    }


}