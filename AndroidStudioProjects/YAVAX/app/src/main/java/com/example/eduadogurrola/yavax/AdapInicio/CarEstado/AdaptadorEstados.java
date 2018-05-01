package com.example.eduadogurrola.yavax.AdapInicio.CarEstado;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.DimencionPantalla;
import com.example.eduadogurrola.yavax.R;

import java.util.LinkedList;

import static com.example.eduadogurrola.yavax.R.id.FotoPerfil;

/**
 * Created by Eduado Gurrola on 07/02/2018.
 */

public class AdaptadorEstados extends RecyclerView.Adapter<AdaptadorEstados.Extender> {

    LinkedList<ListaObjetoLibre> lista;
    Context context;
    Item_eventos_estructura Extruc;
    DimencionPantalla dim;

    /**
     * Selecciona Contenido y contexto
     * @param lista
     * @param context
     */
    public  AdaptadorEstados(LinkedList<ListaObjetoLibre> lista,Context context){
        this.lista=lista;
        this.context=context;
        dim=new DimencionPantalla(context);
    }

    @Override
    public AdaptadorEstados.Extender onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_burbuja_estado, parent, false);
        Extender vh = new Extender(v,context);
        return vh;
    }

    @Override
    public void onBindViewHolder(Extender holder, final int position) {

        Extruc=new Item_eventos_estructura(context,holder,position,lista);
        Extruc.GetDiseño();
        Extruc.GetEventos();


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class Extender extends RecyclerView.ViewHolder {

        Typeface scrip;
        public TextView nombre;
        public NetworkImageView foto;
        public ImageView visto;
        public RelativeLayout fondo;
        public CardView Circulo;

        public Extender(View v,Context context) {
            super(v);


            nombre = v.findViewById(R.id.Nombre);
            foto = v.findViewById(R.id.Imagen);
            visto=v.findViewById(R.id.Visto);
            fondo=v.findViewById(R.id.Fondo);

            //diselo
            Circulo=v.findViewById(R.id.circulo);
            Circulo.setElevation(0);

            if (dim.getHeigth()>=620){//asigna el valor de pantalla mayor con incremento *2
                Circulo.setRadius(45*2);
            }
            else {
                Circulo.setRadius(45);
            }

            String Fuente="Fuente/DKHeadlock.otf";
            this.scrip= Typeface.createFromAsset(context.getAssets(),Fuente);
            nombre.setTypeface(scrip);


        }

    }





}
