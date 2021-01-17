package com.example.kelompokppbbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.kopi, R.drawable.bubble, R.drawable.matcha, R.drawable.water};

    Button pindah;

    Button tmblPesan;
    databaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        BantuDb = new databaseHelper(this);
        tmblPesan = (Button)findViewById(R.id.tmblPesan);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    public void eskopi(View view) {
        Intent intent=new Intent(MainActivity.this,icecoffee.class);
        startActivity(intent);
    }

    public void boba(View view) {
        Intent intent=new Intent(MainActivity.this,bubbletea.class);
        startActivity(intent);
    }

    public void tehijo(View view) {
        Intent intent=new Intent(MainActivity.this,greentea.class);
        startActivity(intent);
    }

    public void airputih(View view) {
        Intent intent=new Intent(MainActivity.this,mineral.class);
        startActivity(intent);
    }

    public void pindah(View view) {
        Intent intent=new Intent(MainActivity.this,pesanan.class);
        startActivity(intent);
    }
}