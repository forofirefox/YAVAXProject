package com.example.eduadogurrola.yavax.AdapInicio.CarRecuerdo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos.BDrecuer;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.DimencionPantalla;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.ImageModel;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.Resolucion;
import com.example.eduadogurrola.yavax.PerfilUsuario.perfil;
import com.example.eduadogurrola.yavax.R;

import java.util.LinkedList;

import static android.media.CamcorderProfile.get;
import static com.example.eduadogurrola.yavax.R.id.Foto;

/**
 * Created by Eduado Gurrola on 09/04/2018.
 */

public class Item_recuerdo_extructura {

    private Context context;
    private AdaptadorRecuerdo.Extender view;
    private int Potition;
    private LinkedList<ListaObjetoLibre> lista;
    private View.OnClickListener Click_listener;

    //Herramientas
    DimencionPantalla dim;
    Resolucion res;
    BDrecuer bDrecuer;



    /**
     * Selecciona las Atrivutos necesarios para le diseño y manipulacion del archivo
     * XML
     * @param context
     * @param view
     * @param Potition
     * @param lista
     */
    public Item_recuerdo_extructura(Context context, AdaptadorRecuerdo.Extender view,
                                   int Potition, LinkedList<ListaObjetoLibre> lista){
        this.context=context;
        this.view=view;
        this.Potition=Potition;
        this.lista=lista;
        dim=new DimencionPantalla(context);
        bDrecuer=new BDrecuer();

    }

    /**
     * Genera los eventos OnlcickListener
     */
    public void GetEventos(){

        view.fotPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                        //Enviar a perfil de la persona
                        Intent Perfil=new Intent(context, perfil.class);
                        context.startActivity(Perfil);
            }
        });
        view.iPublicada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //Creacion de la ventana imagen
                AlertaCreada alertaCreada=new AlertaCreada(context);
                alertaCreada.CreaAleta(R.layout.item_vista_recuerdo,true,true,0);
                ImageView foto=alertaCreada.GetViewTools().findViewById(R.id.foto);
                TextView comentario=alertaCreada.GetViewTools().findViewById(R.id.Comentario);
                TextView vistas=alertaCreada.GetViewTools().findViewById(R.id.vistas);

                //Diseño
                foto.setImageDrawable(view.iPublicada.getDrawable());
                comentario.setText(""+lista.get(Potition).Get(3));
                vistas.setText(""+lista.get(Potition).Get(4));

            }
        });
    }



    /**
     * Genera El diseñoa con la Potition Asignada con tu lista
     */
    public void GetDiseño(){


        //Seleccion del color de la imagen
          int Color= bDrecuer.SetColorAlatorio(Integer.parseInt(lista.get(Potition).Get(7)));
        //
        view.cBarra.setBackgroundColor(context.getResources().getColor(Color));//Sellecion de color
        //Seleccion de los vistos
        view.vistos.setText(""+lista.get(Potition).Get(4));


        DiseImagenPublicada();
    }



    /**
     * Selecciona y incrementa la imagen selecionada a suvir.
     */
    private void DiseImagenPublicada(){

        if (dim.getHeigth()>=620){//asigna el valor de pantalla mayor con incremento *2
            view.iPublicada.getLayoutParams().height=Integer.parseInt(lista.get(Potition).Get(6))*2;//Largo
        }
        else {
            view.iPublicada.getLayoutParams().height=Integer.parseInt(lista.get(Potition).Get(6));//Largo
        }


        //seleccion de imagen y de el ancho de la imagen
        view.iPublicada.getLayoutParams().width=ViewGroup.LayoutParams.MATCH_PARENT;//ancho


        //Crear url
        view.iPublicada.setImageUrl("http://192.168.173.1:8080"+lista.get(Potition).Get(5), ImageModel.getInstance(context).getImageLoader());
        view.fotPerfil.setImageUrl("http://192.168.173.1:8080"+lista.get(Potition).Get(2), ImageModel.getInstance(context).getImageLoader());


    }


}