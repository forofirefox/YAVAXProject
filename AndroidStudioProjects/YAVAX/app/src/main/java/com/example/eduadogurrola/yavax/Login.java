package com.example.eduadogurrola.yavax;

import android.content.Intent;
import android.support.transition.Transition;
import android.support.transition.TransitionSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eduadogurrola.yavax.CarLogin.Logueo.Loguin;
import com.example.eduadogurrola.yavax.CarLogin.Regisrtro.registro;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //Declaracion
    private Button Batras,BiniciarSecion;
    private Button Registrarte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Declaracion de R
        BiniciarSecion=(Button) findViewById(R.id.IniciarSecion);
        Registrarte=(Button) findViewById(R.id.Registrarte);


        //Declaracion de los clic
        BiniciarSecion.setOnClickListener(this);
        Registrarte.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.IniciarSecion:


                Intent Iniciar=new Intent(Login.this, Loguin.class);
                startActivity(Iniciar);
                overridePendingTransition(R.anim.slider_on,R.anim.slider_on);

                finish();

                break;
            case R.id.Registrarte:


                Intent reg=new Intent(Login.this,registro.class);
                startActivity(reg);
                overridePendingTransition(R.anim.slider_on,R.anim.slider_on);

                finish();

                break;
        }
    }
}
