package com.example.eduadogurrola.yavax.CCamara;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.eduadogurrola.yavax.Pestañas.PCamara;
import com.example.eduadogurrola.yavax.Pestañas.Pegga;
import com.example.eduadogurrola.yavax.R;

import static com.example.eduadogurrola.yavax.R.id.tab;

public class Camara_vista extends AppCompatActivity {

    ViewPager pegar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camara_vista);

        pegar=(ViewPager) findViewById(R.id.pegar);

        PeggarFRAN pegga=new PeggarFRAN(getSupportFragmentManager());

        pegar.setAdapter(pegga);


    }
}
