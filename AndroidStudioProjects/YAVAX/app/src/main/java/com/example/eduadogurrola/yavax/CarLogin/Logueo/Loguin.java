package com.example.eduadogurrola.yavax.CarLogin.Logueo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eduadogurrola.yavax.Login;
import com.example.eduadogurrola.yavax.Navegador;
import com.example.eduadogurrola.yavax.R;

public class Loguin extends AppCompatActivity implements View.OnClickListener,VolleyLogin.ListenerLogin {

    //Declaracion de variables
    private CardView CarFondo;
    private Button Batras,BiniciarSecion;
    private EditText usuariotxt,contratxt;
    VolleyLogin vl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loguin);

        //Diseño del loguien
        CarFondo=(CardView) findViewById(R.id.CarFondo);
        CarFondo.setRadius(9);
        CarFondo.setCardElevation(20);

        //Delcaracion
        usuariotxt = (EditText)findViewById(R.id.usuariotxt);
        contratxt = (EditText)findViewById(R.id.contra);
        Batras=(Button) findViewById(R.id.iatras);
        BiniciarSecion=(Button) findViewById(R.id.IniciarSecion);
        vl = new VolleyLogin(getApplicationContext());

        //Declaracion de los cli
        Batras.setOnClickListener(this);
        BiniciarSecion.setOnClickListener(this);
        vl.onListenerPacket(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iatras:

                Intent Atras=new Intent(Loguin.this, Login.class);
                startActivity(Atras);

                break;
            case R.id.IniciarSecion:

                    vl.Method(usuariotxt.getText().toString(),contratxt.getText().toString());
                break;
        }
    }

    @Override
    public void onListenerLogin(boolean estado) {
        if (estado)
        {
            Intent Iniciar=new Intent(Loguin.this, Navegador.class);
            startActivity(Iniciar);
            finish();
        }
        else{
            Toast.makeText(this, "Usuario o contraseña no válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
