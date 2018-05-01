package com.example.eduadogurrola.yavax.PerfilUsuario;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.DimencionPantalla;
import com.example.eduadogurrola.yavax.R;

import static com.example.eduadogurrola.yavax.R.id.lista;

public class perfil extends AppCompatActivity {


    NetworkImageView FotoPerfil;
    CardView Circulo;
    DimencionPantalla dim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        dim=new DimencionPantalla(this);

        FotoPerfil=(NetworkImageView) findViewById(R.id.FotoPerfil);

        //diselo
        Circulo=(CardView) findViewById(R.id.circulo);
        Circulo.setElevation(0);

        if (dim.getHeigth()>=620){//asigna el valor de pantalla mayor con incremento *2
            Circulo.setRadius(65*2);
        }
        else {
            Circulo.setRadius(65);
        }

        FotoPerfil.setImageResource(R.drawable.camaratol);


    }



}
