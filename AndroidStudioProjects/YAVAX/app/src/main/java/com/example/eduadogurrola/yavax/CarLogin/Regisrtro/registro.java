package com.example.eduadogurrola.yavax.CarLogin.Regisrtro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.eduadogurrola.yavax.CarLogin.Logueo.Loguin;
import com.example.eduadogurrola.yavax.Login;
import com.example.eduadogurrola.yavax.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eduadogurrola.yavax.R.id.correo;

public class registro extends AppCompatActivity implements View.OnClickListener{

    Button registrar;
    EditText nombre,apellidos,usuario,contra,telefono,correo;
    RequestQueue reQuest;
    String URL_post="http://192.168.173.1:8080/registrar";
    JSONObject  objeto;

    Button Batras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        reQuest= Volley.newRequestQueue(this);

        nombre=(EditText)findViewById(R.id.nombre);
        apellidos=(EditText)findViewById(R.id.apellido);
        usuario=(EditText)findViewById(R.id.Usuario);
        contra=(EditText)findViewById(R.id.contra);
        telefono=(EditText)findViewById(R.id.telefono);
        registrar=(Button)findViewById(R.id.sig);
        correo=(EditText) findViewById(R.id.correo);

        Batras=(Button) findViewById(R.id.iatras);


        Batras.setOnClickListener(this);
        registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.iatras:

                Intent Atras=new Intent(getBaseContext(), Login.class);
                startActivity(Atras);


                break;
            case R.id.sig:
            {
                if (contra.getText().toString().length()>6){

                    try {
                        objeto= new JSONObject();
                        objeto.put("nombre", nombre.getText().toString());
                        objeto.put("apellidos",apellidos.getText().toString());
                        objeto.put("usuario", usuario.getText().toString());
                        objeto.put("contra", contra.getText().toString());
                        objeto.put("correo", correo.getText().toString());
                        objeto.put("telefono", telefono.getText().toString());

                    } catch (JSONException e) {
                        Toast.makeText(this, "error entrada", Toast.LENGTH_SHORT).show();
                        Log.e("Error",e.getMessage());

                    }

                    JsonObjectRequest jsonobject= new JsonObjectRequest(URL_post, objeto, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(getBaseContext(),"Registro Exitoso",Toast.LENGTH_SHORT).show();

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    Intent Atras=new Intent(getBaseContext(), Login.class);
                                    startActivity(Atras);

                                }

                            });
                    jsonobject.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    reQuest.add(jsonobject);

                }
                else {
                    Toast.makeText(this, "Contraseña muy corta", Toast.LENGTH_SHORT).show();

                }


            }


        }

    }
}