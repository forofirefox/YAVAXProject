package com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos;

import android.content.Context;

import com.example.eduadogurrola.yavax.Herramientas.Gibase.GiRespuestaJson;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.Selecciona;

import java.util.LinkedList;

/**
 * Created by Eduado Gurrola on 23/04/2018.
 */

public class BDIconos {
    //Girespuesta json
    GiRespuestaJson giRespuestaJson=new GiRespuestaJson();

    /**
     * Llama a tosos los recuerdos con la consulta de la base
     * @param context
     */
    public LinkedList<ListaObjetoLibre> SetAll(Context context){
        giRespuestaJson=new GiRespuestaJson(Selecciona.Methodo.Read,context,"http://192.168.173.1:8080/iconos", Selecciona.Tipo.Array);

        //Retorna la lista ya llena
        return giRespuestaJson.GetListaCompleta();
    }

    /**
     * Llama a tosos los recuerdos con la consulta de la base
     * @param context
     */
    public LinkedList<ListaObjetoLibre> GetLista(Context context){
        //Retorna la lista ya llena
        return giRespuestaJson.GetListaCompleta();
    }

    /**
     * Verifica lista
     */
    public boolean Verificar(){
        if(giRespuestaJson.GetListaCompleta().size()!=0) {
            return true;
        }
        else{
            return false;
        }
    }

}
