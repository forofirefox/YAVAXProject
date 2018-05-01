package com.example.eduadogurrola.yavax.CrearEvento.AdapIconos;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.eduadogurrola.yavax.AdapInicio.CarEstado.Item_eventos_estructura;
import com.example.eduadogurrola.yavax.AdapInicio.CarRecuerdo.Item_recuerdo_extructura;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.DimencionPantalla;
import com.example.eduadogurrola.yavax.R;

import java.util.LinkedList;

/**
 * Created by Eduado Gurrola on 23/04/2018.
 */

public class AdaptadorIconos extends RecyclerView.Adapter<AdaptadorIconos.viw> {

    DimencionPantalla dim;
    LinkedList<ListaObjetoLibre> lista;
    Context context;
    item_iconos_extructura Extruc;
    AlertaCreada Alerta;
    ImageView Foto;

    /**
     * Selecciona Contenido y contexto
     * @param lista
     * @param context
     * @param aler
     * @param foto
     */
    public  AdaptadorIconos(LinkedList<ListaObjetoLibre> lista, Context context, AlertaCreada aler, ImageView foto){
        this.lista=lista;
        this.context=context;
        dim=new DimencionPantalla(context);
        Alerta=aler;
        Foto=foto;
    }


    public class viw extends RecyclerView.ViewHolder {

        NetworkImageView Icono;

        public viw(View itemView) {
            super(itemView);

            Icono=itemView.findViewById(R.id.Icono);

        }
    }

    @Override
    public AdaptadorIconos.viw onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento_foto_marco,parent,false);

        viw Nuevo=new viw(view);

        return Nuevo;
    }

    @Override
    public void onBindViewHolder(AdaptadorIconos.viw holder, int position) {

        Extruc=new item_iconos_extructura(context,holder,position,lista,Alerta,Foto);
        Extruc.GetDiseño();
        Extruc.GetEventos();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
