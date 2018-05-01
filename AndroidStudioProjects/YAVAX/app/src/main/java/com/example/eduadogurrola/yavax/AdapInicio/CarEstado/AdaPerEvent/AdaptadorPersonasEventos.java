package com.example.eduadogurrola.yavax.AdapInicio.CarEstado.AdaPerEvent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.eduadogurrola.yavax.AdapInicio.CarEstado.Item_eventos_estructura;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.DimencionPantalla;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.ImageModel;
import com.example.eduadogurrola.yavax.R;

import java.util.LinkedList;

/**
 * Created by Eduado Gurrola on 26/04/2018.
 */

public class AdaptadorPersonasEventos extends RecyclerView.Adapter<AdaptadorPersonasEventos.Exten> {

    LinkedList<ListaObjetoLibre> lista;
    Context context;
    DimencionPantalla dim;
    int Id;
    /**
     * Selecciona Contenido y contexto
     * @param lista
     * @param context
     */
    public  AdaptadorPersonasEventos(LinkedList<ListaObjetoLibre> lista,Context context,int Id){
        this.lista=lista;
        this.context=context;
        this.Id=Id;
        dim=new DimencionPantalla(context);

    }

    @Override
    public AdaptadorPersonasEventos.Exten onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_amigos,parent,false);

        Exten exten=new Exten(view);

        return exten;
    }

    @Override
    public void onBindViewHolder(AdaptadorPersonasEventos.Exten holder, int position) {

        Toast.makeText(context, ""+Id, Toast.LENGTH_SHORT).show();
            if (Integer.parseInt(lista.get(position).Get(0))==Id){
                holder.Nombre.setText(lista.get(position).Get(1));
                holder.FotoPersona.setImageUrl("http://192.168.173.1:8080"+lista.get(position).Get(2), ImageModel.getInstance(context).getImageLoader());
            }


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class Exten extends RecyclerView.ViewHolder{

        NetworkImageView FotoPersona;
        TextView Nombre;

        public Exten(View itemView) {
            super(itemView);

            FotoPersona=itemView.findViewById(R.id.foto);
            Nombre=itemView.findViewById(R.id.nom);

        }
    }
}
