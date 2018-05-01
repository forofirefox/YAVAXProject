package com.example.eduadogurrola.yavax.CrearEvento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eduadogurrola.yavax.R;

import static com.example.eduadogurrola.yavax.R.id.sig;

public class crearEvento extends AppCompatActivity implements View.OnClickListener {


    Button Sigui;
    EditText Nombre;
    EditText Telefono;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        Nombre=(EditText) findViewById(R.id.textnom);
        Telefono=(EditText) findViewById(R.id.telefonotxt);





        //Declaracion
        Sigui=(Button) findViewById(R.id.siguiente);


        //Clic
        Sigui.setOnClickListener(this);

        TimerValides();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.siguiente:

                SharedPreferences guarda=getSharedPreferences("Evento",this.MODE_APPEND);
                SharedPreferences.Editor cambiar=guarda.edit();

                cambiar.putString("Nombre",""+Nombre.getText());
                cambiar.putString("Telefono",""+Telefono.getText());

                cambiar.commit();

                Intent Ubi=new Intent(getApplicationContext(),Ubicacion_Grup.class);
                startActivity(Ubi);
                break;

        }

    }


    public void TimerValides(){
        CountDownTimer countDownTimer=new CountDownTimer(1000*1,1000) {
            @Override
            public void onTick(long l) {

                validar();
            }

            @Override
            public void onFinish() {

                TimerValides();
            }
        }.start();
    }

    public void validar(){

        if (Nombre.getText().toString().isEmpty()||Telefono.getText().toString().isEmpty()){
            Sigui.setEnabled(false);
        }
        else {
            Sigui.setEnabled(true);

        }


    }


}
