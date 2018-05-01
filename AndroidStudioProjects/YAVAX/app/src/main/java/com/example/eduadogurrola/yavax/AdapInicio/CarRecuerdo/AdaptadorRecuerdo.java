package com.example.eduadogurrola.yavax.AdapInicio.CarRecuerdo;

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
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.DimencionPantalla;
import com.example.eduadogurrola.yavax.R;

import java.util.LinkedList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.eduadogurrola.yavax.R.id.lista;

/**
 * Created by Eduado Gurrola on 09/04/2018.
 */

public class AdaptadorRecuerdo extends RecyclerView.Adapter<AdaptadorRecuerdo.Extender>{

    DimencionPantalla dim;
    LinkedList<ListaObjetoLibre> lista;
    Context context;
    Item_recuerdo_extructura Extruc;


    /**
     * Selecciona Contenido y contexto
     * @param lista
     * @param context
     */
    public  AdaptadorRecuerdo(LinkedList<ListaObjetoLibre> lista,Context context){
        this.lista=lista;
        this.context=context;
        dim=new DimencionPantalla(context);
    }


    @Override
    public Extender onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recuerdo,parent,false);
        Extender vh=new Extender(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(Extender holder, int position) {

        Extruc=new Item_recuerdo_extructura(context,holder,position,lista);
        Extruc.GetEventos();
        Extruc.GetDiseño();

    }


    @Override
    public int getItemCount() {
        return lista.size();
    }



    public class Extender extends RecyclerView.ViewHolder {

        CardView Cmarco;
        CardView FotoPerfil;
        NetworkImageView fotPerfil;
        NetworkImageView iPublicada;
        LinearLayout cBarra;
        TextView vistos;

        public Extender(View itemView) {
            super(itemView);

            //Foto de pergil
            fotPerfil=itemView.findViewById(R.id.foto);
            iPublicada=itemView.findViewById(R.id.Ipublicada);
            cBarra=itemView.findViewById(R.id.cbarra);
            vistos=itemView.findViewById(R.id.vistas);


            //marco
            Cmarco=itemView.findViewById(R.id.marco);
            Cmarco.setRadius(10);
            Cmarco.setElevation(0);
            Cmarco.setCardBackgroundColor(Color.TRANSPARENT);

            //Foto
            FotoPerfil=itemView.findViewById(R.id.FotoPerfil);
            if (dim.getHeigth()>=620){//asigna el valor de pantalla mayor con incremento *2
                FotoPerfil.setRadius(30*2);
            }
            else {
                FotoPerfil.setRadius(30);
            }

            FotoPerfil.setElevation(0);
            FotoPerfil.setCardBackgroundColor(Color.WHITE);

        }
    }
}