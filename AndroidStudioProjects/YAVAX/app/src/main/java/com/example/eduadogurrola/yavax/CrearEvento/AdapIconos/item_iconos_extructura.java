package com.example.eduadogurrola.yavax.CrearEvento.AdapIconos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.eduadogurrola.yavax.AdapInicio.CarEstado.AdaptadorEstados;
import com.example.eduadogurrola.yavax.AdapInicio.Item_add_evento.Citem_add_evento;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.ImageModel;
import com.example.eduadogurrola.yavax.R;

import java.util.LinkedList;

import static com.example.eduadogurrola.yavax.R.id.HoraI;
import static com.example.eduadogurrola.yavax.R.id.HoraT;

/**
 * Created by Eduado Gurrola on 23/04/2018.
 */

public class item_iconos_extructura {


    //variables
    private Context context;
    private AlertaCreada Alerta;
    private AdaptadorIconos.viw view;
    private int Potition;
    private LinkedList<ListaObjetoLibre> lista;
    ImageView Foto;

    AlertaCreada alertaCreada;

    /**
     * Selecciona las Atrivutos necesarios para le diseño y manipulacion del archivo
     * XML
     * @param context
     * @param view
     * @param Potition
     * @param lista
     * @param alerta
     * @param foto
     */
    public item_iconos_extructura(Context context, AdaptadorIconos.viw view,
                                  int Potition, LinkedList<ListaObjetoLibre> lista, AlertaCreada alerta, ImageView foto){
        this.context=context;
        this.view=view;
        this.Potition=Potition;
        this.lista=lista;
        Alerta=alerta;
        Foto=foto;

    }


    /**
     * Genera los eventos OnlcickListener
     */
    public void GetEventos(){


        view.Icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                SharedPreferences guarda=context.getSharedPreferences("Evento",context.MODE_APPEND);
                SharedPreferences.Editor cambiar=guarda.edit();

                cambiar.putString("Foto",""+lista.get(Potition).Get(0));

                cambiar.commit();
                Alerta.AlertHide();
                Foto.setImageDrawable(view.Icono.getDrawable());

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


    }

    /**
     * Imagen Redondiada
     */
    private void SetImaCircu(){
        view.Icono.setImageUrl("http://192.168.173.1:8080"+lista.get(Potition).Get(1), ImageModel.getInstance(context).getImageLoader());
        GetDiseEven();
    }

    /**
     * Evento Sin visualizar
     */
    private void GetDiseEven(){

    }



}
