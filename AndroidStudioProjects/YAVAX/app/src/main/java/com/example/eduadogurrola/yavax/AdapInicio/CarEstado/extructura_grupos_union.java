package com.example.eduadogurrola.yavax.AdapInicio.CarEstado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.eduadogurrola.yavax.AdapInicio.CarEstado.AdaPerEvent.AdaptadorPersonasEventos;
import com.example.eduadogurrola.yavax.AdapInicio.CarRecuerdo.AdaptadorRecuerdo;
import com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos.BDdatosEvento;
import com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos.BDperEvent;
import com.example.eduadogurrola.yavax.CrearEvento.crearEvento;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.DimencionPantalla;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.ImageModel;
import com.example.eduadogurrola.yavax.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.eduadogurrola.yavax.R.drawable.usuario;
import static com.example.eduadogurrola.yavax.R.id.CrearEvento;
import static com.example.eduadogurrola.yavax.R.id.contra;
import static com.example.eduadogurrola.yavax.R.id.correo;
import static com.example.eduadogurrola.yavax.R.id.lista;
import static com.example.eduadogurrola.yavax.R.id.nombre;
import static com.example.eduadogurrola.yavax.R.id.telefono;
import static com.example.eduadogurrola.yavax.R.id.unirse;
import static java.security.AccessController.getContext;

/**
 * Created by Eduado Gurrola on 26/04/2018.
 */

public class extructura_grupos_union implements View.OnClickListener {
    private View view;
    private Context context;
    private AlertDialog dialog;
    private CardView Circulo;
    private  Button Unirse;
    private NetworkImageView fotoevento,ImagenCreador;
    private AlertaCreada Hide;
    private TextView CantP,NombreCreador,Termino,Nombre,Unidos;
    RelativeLayout Error;
    private RecyclerView LisPersonas;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //
    String URL_post="http://192.168.173.1:8080/unirgrupo";
    JSONObject  objeto;
    RequestQueue reQuest;


    //mODIFIACION DE LAS TABLAS
    BDdatosEvento bDdatosEvento;
    DimencionPantalla dim;
    AdaptadorPersonasEventos adaptador;
    BDperEvent bDperEvent;
    int idPersona;
    //Id para consulta
    int Id;

    public extructura_grupos_union(View view, Context context, AlertDialog dialog, AlertaCreada Hide,int Id){
        this.view=view;
        this.context=context;
        this.dialog=dialog;
        this.Hide=Hide;
        this.Id=Id;

        reQuest= Volley.newRequestQueue(context);


        SharedPreferences guarda=context.getSharedPreferences("Evento",context.MODE_APPEND);
        idPersona=guarda.getInt("Id_",0);

        bDdatosEvento=new BDdatosEvento();
        bDperEvent=new BDperEvent();

        dim=new DimencionPantalla(context);
        Parcear();

        //timer
        timer();
        //Crear evento
        bDdatosEvento.SetAll(context);

    }

    private void Parcear(){

        //Datos a modigicar
        Unirse = view.findViewById(R.id.unirse);
        fotoevento=view.findViewById(R.id.Foto);
        CantP=view.findViewById(R.id.CantidadPersonas);
        ImagenCreador=view.findViewById(R.id.ImagenCreador);
        NombreCreador=view.findViewById(R.id.NombreCreador);
        Termino=view.findViewById(R.id.Termino);
        Error=view.findViewById(R.id.Error);
        Nombre=view.findViewById(R.id.Nombre);
        Unidos=view.findViewById(R.id.Unidos);

        LisPersonas=view.findViewById(R.id.Participantes);


        //Asignacion del tamaño
        LisPersonas.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        LisPersonas.setLayoutManager(layoutManager);
        //LLenado de la apliacion
        adaptador=new AdaptadorPersonasEventos(bDperEvent.SetAll(context),context,Id);
        LisPersonas.setAdapter(adaptador);



        //diselo
        Circulo=(CardView) view.findViewById(R.id.circulo);
        Circulo.setElevation(0);

        if (dim.getHeigth()>=620){//asigna el valor de pantalla mayor con incremento *2
            Circulo.setRadius(65*2);
        }
        else {
            Circulo.setRadius(65);
        }
        //llega la foto
        Unirse.setOnClickListener(this);

    }


    private void Diseño(){



        for (int i = 0; i < bDdatosEvento.GetLista(context).size() ; i++) {
            if (Id==Integer.parseInt(bDdatosEvento.GetLista(context).get(i).Get(0))){

                if (Integer.parseInt(bDdatosEvento.GetLista(context).get(i).Get(11))==idPersona){
                    Unirse.setText("Mi grupo");
                    Unirse.setEnabled(false);
                }
                else{

                }

                fotoevento.setImageUrl("http://192.168.173.1:8080"+bDdatosEvento.GetLista(context).get(i).Get(10), ImageModel.getInstance(context).getImageLoader());
                CantP.setText(""+0);
                ImagenCreador.setImageUrl("http://192.168.173.1:8080"+bDdatosEvento.GetLista(context).get(i).Get(2), ImageModel.getInstance(context).getImageLoader());
                NombreCreador.setText(""+bDdatosEvento.GetLista(context).get(i).Get(1));
                Termino.setText(""+bDdatosEvento.GetLista(context).get(i).Get(4));
                Nombre.setText(""+bDdatosEvento.GetLista(context).get(i).Get(3));
                Unidos.setText(""+bDdatosEvento.GetLista(context).get(i).Get(6));



                break;
            }
        }




    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.unirse:
                Hide.AlertHide();
                SharedPreferences guarda=context.getSharedPreferences("Evento",context.MODE_APPEND);
                SharedPreferences.Editor cambiar=guarda.edit();

                cambiar.putInt("Id_evento",Id);
                cambiar.commit();

                try {
                    objeto= new JSONObject();
                    objeto.put("fkevento", Id);
                    objeto.put("fkpersona", idPersona);



                } catch (JSONException e) {
                    Toast.makeText(context, "error entrada", Toast.LENGTH_SHORT).show();
                    Log.e("Error",e.getMessage());

                }

                JsonObjectRequest jsonobject= new JsonObjectRequest(URL_post, objeto, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context,"Registro Exitoso",Toast.LENGTH_SHORT).show();

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.e("Error",error.getMessage());

                            }

                        });
                jsonobject.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                reQuest.add(jsonobject);


                break;

        }
    }

    public void timer(){
        CountDownTimer countDownTimer=new CountDownTimer(1000*5,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(bDdatosEvento.Verificar()!=true){
                    timer();
                    Error.setVisibility(View.VISIBLE);
                }
                else {
                    Diseño();//Modifiacion del diseño

                    layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                    LisPersonas.setLayoutManager(layoutManager);
                    //LLenado de la apliacion
                    if (bDperEvent.GetLista(context).size()>0){
                        for (int i = 0; i < bDperEvent.GetLista(context).size(); i++) {
                           if(Integer.parseInt(bDperEvent.GetLista(context).get(i).Get(3))==idPersona){
                               Unirse.setText("Unido");
                               Unirse.setEnabled(false);
                               break;
                           }
                        }

                        adaptador=new AdaptadorPersonasEventos(bDperEvent.GetLista(context),context,Id);
                        LisPersonas.setAdapter(adaptador);
                        Error.setVisibility(View.INVISIBLE);
                    }
                    else {
                        Error.setVisibility(View.VISIBLE);

                    }

                    //Error.setVisibility(View.VISIBLE);
                }

            }
        }.start();
    }

}
