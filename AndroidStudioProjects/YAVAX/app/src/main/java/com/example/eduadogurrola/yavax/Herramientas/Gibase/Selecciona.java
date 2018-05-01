package com.example.eduadogurrola.yavax.Herramientas.Gibase;

import android.content.Context;

/**
 * Created by Eduado Gurrola on 08/03/2018.
 */

public abstract class Selecciona {

    /*Contexto de la aplicacion*/
    private Context context;
        /*Url de la navegacion*/
    private String url;
        /*Tipo de metodo a usar*/
    private int get;
        /*Seleccion de tipo de navegacion*/
    private int tipo;

        public Selecciona() {/*Seleccion libre*/}

        public Selecciona(int get, String url, Context context,int Tipo) {/*Seleccion Con datos accignados*/
            this.context=context;
            this.url=url;
            this.get=get;
            this.tipo=Tipo;
        }


    /**
     * Interface con los tipos de methodos que puedes aplicar
     */
    public interface Methodo {
            int GET = 0;
            int POST = 1;
            int PUT = 2;
            int DELETE = 3;
            int HEAD = 4;
            int OPTIONS = 5;
            int TRACE = 6;
            int PATCH = 7;
            int Read=8;
        }

    /**
     * Seleccion tipo de json
     */
    public interface Tipo {
        int Objet = 0;
        int Array = 1;
        int String=2;
    }



    }
