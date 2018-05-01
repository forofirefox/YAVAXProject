package com.example.eduadogurrola.yavax.AdapInicio.CarEstado;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.eduadogurrola.yavax.AdapInicio.Item_add_evento.Citem_add_evento;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.ImageModel;
import com.example.eduadogurrola.yavax.Navegador;
import com.example.eduadogurrola.yavax.R;

import java.util.LinkedList;

/**
 * Created by Eduado Gurrola on 10/02/2018.
 */

public class Item_eventos_estructura{
    //variables
   private Context context;
   private AlertDialog dialog;
   private AdaptadorEstados.Extender view;
   private int Potition;
   private LinkedList<ListaObjetoLibre> lista;
    private View.OnClickListener Click_listener;
    private Citem_add_evento citem_add_evento;

    AlertaCreada alertaCreada;

    /**
     * Selecciona las Atrivutos necesarios para le diseño y manipulacion del archivo
     * XML
     * @param context
     * @param view
     * @param Potition
     * @param lista
     */
    public Item_eventos_estructura(Context context, AdaptadorEstados.Extender view,
                                   int Potition, LinkedList<ListaObjetoLibre> lista){
        this.context=context;
        this.view=view;
        this.Potition=Potition;
        this.lista=lista;


    }



    /**
     * Genera los eventos OnlcickListener
     */
    public void GetEventos(){

        view.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,""+Potition,Toast.LENGTH_LONG).show();


                        alertaCreada=new AlertaCreada(context);
                        alertaCreada.CreaAleta(R.layout.item_evento_publico,true,true,1);
                        extructura_grupos_union ex=new extructura_grupos_union(alertaCreada.GetViewTools(),context,dialog,alertaCreada,Integer.parseInt(lista.get(Potition).Get(0)));

            }
        });
    }



    /**
     * Genera El diseñoa con la Potition Asignada con tu lista
     */
    public void GetDiseño(){

                    SetImaCircu();

    }
    /**
     * Diseño Del more
     */
    private void GetDiseñoAddEvento(){



        view.foto.setBackgroundResource(R.drawable.more);
        view.nombre.setText("Agrega\nEvento");
        view.nombre.setTextColor(context.getResources().getColor(R.color.gris));
        view.nombre.setTextSize(10);
        view.visto.setVisibility(View.INVISIBLE);
    }

    /**
     * Imagen Redondiada
     */
    private void SetImaCircu(){
        view.foto.setImageUrl("http://192.168.173.1:8080"+lista.get(Potition).Get(2), ImageModel.getInstance(context).getImageLoader());

        GetDiseEven();
    }

    /**
     * Evento Sin visualizar
     */
    private void GetDiseEven(){
        view.visto.setVisibility(View.VISIBLE);
        view.nombre.setTextColor(context.getResources().getColor(R.color.NegroBajo));
        view.nombre.setTextSize(15);
        view.nombre.setText(""+lista.get(Potition).Get(1));
    }

    private void Timer(){
        CountDownTimer countDownTimer=new CountDownTimer(2/1000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                dialog.hide();
            }
        }.start();
    }


}
