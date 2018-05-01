package com.example.eduadogurrola.yavax.CrearEvento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduadogurrola.yavax.R;

public class Cover extends AppCompatActivity implements View.OnClickListener {


    Button Sigui;

    EditText Number;
    EditText Precio;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        //Declaracion
        Number=(EditText) findViewById(R.id.cantidadp);
        Precio=(EditText) findViewById(R.id.precio) ;
        total=(TextView) findViewById(R.id.total);


        Sigui=(Button) findViewById(R.id.siguiente);


        //Clic
        Sigui.setOnClickListener(this);
        Number.setOnClickListener(this);
        Precio.setOnClickListener(this);

        TimerDnero();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.siguiente:
                if (!Number.getText().toString().isEmpty() || !Precio.getText().toString().isEmpty()) {


                SharedPreferences guarda=getSharedPreferences("Evento",this.MODE_APPEND);
                SharedPreferences.Editor cambiar=guarda.edit();

                cambiar.putString("CantidadP",""+Number.getText());
                cambiar.putString("PrecioC",""+Precio.getText());

                cambiar.commit();

                Intent Ubi=new Intent(getApplicationContext(),fechas_privacidad.class);
                startActivity(Ubi);
                }
                else{
                    Toast.makeText(this, "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cantidadp:
                calcular();

                break;
            case R.id.precio:

                calcular();

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        double cantidad= Double.parseDouble(""+ Number.getText());
        double precio= Double.parseDouble(""+ Number.getText());

        total.setText(""+(cantidad*precio));

    }


    public void TimerDnero(){
        CountDownTimer countDownTimer=new CountDownTimer(1000*1,1000) {
            @Override
            public void onTick(long l) {
                calcular();
            }

            @Override
            public void onFinish() {
                TimerDnero();
            }
        }.start();
    }

    public void calcular(){
        try {
            double cantidad= Double.parseDouble(""+ Number.getText());
            double precio= Double.parseDouble(""+ Precio.getText());

            total.setText(""+(cantidad*precio));
        }
        catch (Exception e){

        }

    }
}
