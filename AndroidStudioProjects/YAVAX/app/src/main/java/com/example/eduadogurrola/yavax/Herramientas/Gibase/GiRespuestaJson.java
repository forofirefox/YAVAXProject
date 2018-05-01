package com.example.eduadogurrola.yavax.Herramientas.Gibase;

import android.content.Context;
import android.widget.Toast;

import java.util.LinkedList;

/**
 * Created by Eduado Gurrola on 08/03/2018.
 */

public class GiRespuestaJson extends Selecciona{

        /*Url de nevagicon */
    private String sUri;
        /*Seleccion del contexto */
    private Context context;
        /*Url de nevagicon */
    private int iGet;
        /*Methodo a usar */
        private int iTipo;
    /*Methodo a usar */

    LinkedList<ListaObjetoLibre> list=new LinkedList<>(); //Selecciona el arreglo

    GiExtJSON Al=new GiExtJSON();//Inicia la Recuperacion del modelo no creado

    public GiRespuestaJson (){ //Solo datos simples
        super();
    }

    /**
     * Selecciona el tipo de Methodo y los siguientes parametros
     * @param iGet Seleccion.Methodo.....
     * @param context Ingresael contexto
     * @param Url Usa una uri que devuelva datos
     * @param Tipo Seleccion.Tipo......
     */
    public GiRespuestaJson(int iGet, Context context, String Url,int Tipo)  { //Solo con datos
        super(iGet,Url,context,Tipo);
        this.iGet=iGet;
        this.sUri=Url;
        this.context=context;
        this.iTipo=Tipo;
        CrearDatos();
    }

    /**
     * El siguiente metodo nos devuelve la creaciion del
     * sup modelo ya que aun falta la creacion
     * propia de este
     */
    private void CrearDatos(){

        Al.SetMethod(iGet,context,sUri,iTipo);
    }

    /**
     * Retorna la lista creada con las columnas y datos llenos
     * @return
     */
    public LinkedList<ListaObjetoLibre> RetornaLista(){
        CrearDatos();
        return  list=Al.GetLista();
    }

    /**
     * Retorna la lista creada con las columnas y datos llenos
     * @return
     */
    public LinkedList<ListaObjetoLibre> GetListaCompleta(){
        return  list=Al.GetLista();
    }

    public int GetNum(){
        return Integer.parseInt(list.get(0).Get(0));
    }
}
