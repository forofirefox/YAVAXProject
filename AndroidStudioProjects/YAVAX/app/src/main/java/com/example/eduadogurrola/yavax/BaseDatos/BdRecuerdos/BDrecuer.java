package com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos;

import android.content.Context;
import android.widget.Toast;

import com.example.eduadogurrola.yavax.Herramientas.Gibase.GiRespuestaJson;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.ListaObjetoLibre;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.Selecciona;
import com.example.eduadogurrola.yavax.R;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Eduado Gurrola on 16/04/2018.
 */

public class BDrecuer {
    //Girespuesta json
    GiRespuestaJson giRespuestaJson=new GiRespuestaJson();

    /**
     * Llama a tosos los recuerdos con la consulta de la base
     * @param context
     */
    public LinkedList<ListaObjetoLibre> SetAllRecuerdos(Context context){
        giRespuestaJson=new GiRespuestaJson(Selecciona.Methodo.Read,context,"http://192.168.173.1:8080/imgs",Selecciona.Tipo.Array);

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


    int[] color=new int[]{R.color.Cazul,R.color.Cgris,R.color.Crojo,R.color.Crosa,R.color.Cverde,R.color.Camari};


     /**
     * Selecciona el color de la imagen
     * @return
     */
    public int SetColorAlatorio(int col){

        return color[col];

    }


/* private int SeleccionTamaño(){

            if (LisAcopla[0]==false){
                LisAcopla[0]=true;
                return Tamaños[1];
            }
            else if (LisAcopla[1]==false){
                LisAcopla[1]=true;
                return Tamaños[0];
            }
            else if (LisAcopla[2]==false){
                LisAcopla[2]=true;
                return Tamaños[1];
            }
            else if (LisAcopla[3]==false){
                for (int i = 0; i < LisAcopla.length; i++) {
                    LisAcopla[0]=false;
                }
                return Tamaños[0];
            }
            else {
                return 0;
            }

    }*/

}
