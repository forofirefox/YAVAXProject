package com.example.eduadogurrola.yavax.CrearEvento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos.BDIconos;
import com.example.eduadogurrola.yavax.CarLogin.Logueo.Loguin;
import com.example.eduadogurrola.yavax.CrearEvento.AdapIconos.AdaptadorIconos;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.Navegador;
import com.example.eduadogurrola.yavax.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eduadogurrola.yavax.R.drawable.usuario;
import static com.example.eduadogurrola.yavax.R.id.HoraI;
import static com.example.eduadogurrola.yavax.R.id.HoraT;
import static com.example.eduadogurrola.yavax.R.id.contra;
import static com.example.eduadogurrola.yavax.R.id.correo;
import static com.example.eduadogurrola.yavax.R.id.nombre;
import static com.example.eduadogurrola.yavax.R.id.telefono;

public class Foto_evento extends AppCompatActivity implements View.OnClickListener,Response.Listener<JSONObject>,Response.ErrorListener{




    AdaptadorIconos adaptadorIconos;
    BDIconos bdIconos;
     AlertaCreada alertaCreada;
    CountDownTimer countDownTimer;
    RecyclerView Listicon;
    ImageView Foto;
    EditText descrip;
    Button sig;
    JSONObject  objeto;
    RequestQueue reQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_evento);

        reQuest= Volley.newRequestQueue(this);

        bdIconos=new BDIconos();

        Foto=(ImageView) findViewById(R.id.fotos);
        sig=(Button) findViewById(R.id.siguiente);

        descrip=(EditText) findViewById(R.id.Descip);

        Foto.setOnClickListener(this);
        sig.setOnClickListener(this);




    }


    public void CrearAlerta(){
         alertaCreada=new AlertaCreada(this);
         alertaCreada.CreaAleta(R.layout.itam_evento_foto,true,false,0);


        SetListaIconos(alertaCreada.GetViewTools());
        timer();


    }

    /**
     * Creacion de la lista Recuerdos
     * @param view
     */
    private void SetListaIconos(View view){
        //Creacion lista Rcuerdos
         Listicon=(RecyclerView) view.findViewById(R.id.iconos);
        Listicon.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager2=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        Listicon.setLayoutManager(layoutManager2);//
        //LLenado de la apliacion

        adaptadorIconos = new AdaptadorIconos(bdIconos.SetAll(getBaseContext()),getBaseContext(),alertaCreada,Foto);
        Listicon.setAdapter(adaptadorIconos);


    }

    public void timer(){
         countDownTimer=new CountDownTimer(1000*5,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(bdIconos.Verificar()!=true){
                    timer();
                }
                else {
                    adaptadorIconos = new AdaptadorIconos(bdIconos.GetLista(getBaseContext()),getBaseContext(),alertaCreada, Foto);
                    Listicon.setAdapter(adaptadorIconos);
                }

            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.siguiente:
                SharedPreferences guarda=getSharedPreferences("Evento",this.MODE_APPEND);
                int Id=guarda.getInt("Id_",0);
                String nombre=  guarda.getString("Nombre","");
                String tel=  guarda.getString("Telefono","");
                String lat=  guarda.getString("Lat","");
                String lon=  guarda.getString("Lng","");
                String cp= guarda.getString("CantidadP","");
                String pc= guarda.getString("PrecioC","");
                String dia=  guarda.getString("Dia","");
                String hoi= guarda.getString("HoraI","");
                String hot=  guarda.getString("HoraT","");
                String foto=  guarda.getString("Foto","");
                String descri=  descrip.getText().toString();
                
                if (foto.isEmpty() || descrip.getText().toString().isEmpty()){
                    Toast.makeText(this, "Faltan Datos", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, ""+nombre, Toast.LENGTH_SHORT).show();

                    String URL_post="http://192.168.173.1:8080/reggrup";





                    try {
                        objeto= new JSONObject();
                        objeto.put("IdP", Id);
                        objeto.put("Nombre", nombre);
                        objeto.put("Telefono",tel);
                        objeto.put("Lat", lat);
                        objeto.put("Lng",lon);
                        objeto.put("CantidadP",Integer.parseInt(cp));
                        objeto.put("PrecioC",Integer.valueOf(pc));
                        objeto.put("Dia",dia);
                        objeto.put("HoraI", hoi);
                        objeto.put("HoraT", hot);
                        objeto.put("Foto", Integer.parseInt(foto));
                        objeto.put("des", descri);



                    } catch (JSONException e) {
                        Toast.makeText(this, "error entrada", Toast.LENGTH_SHORT).show();
                        Log.e("Error",e.getMessage());

                    }

                    JsonObjectRequest jsonobject= new JsonObjectRequest(URL_post, objeto, this,this);

                    jsonobject.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    reQuest.add(jsonobject);


                    Intent Iniciar=new Intent(this, Navegador.class);
                    startActivity(Iniciar);                }
              

              


                break;
            case R.id.fotos:
                CrearAlerta();
                break;

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(this, "Creado", Toast.LENGTH_SHORT).show();
    }
}
