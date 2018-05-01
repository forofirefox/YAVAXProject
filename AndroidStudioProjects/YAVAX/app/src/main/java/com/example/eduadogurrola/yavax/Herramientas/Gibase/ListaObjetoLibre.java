package com.example.eduadogurrola.yavax.Herramientas.Gibase;

/**
 * Created by Eduado Gurrola on 08/03/2018.
 */

public class ListaObjetoLibre {

    private String[] Colum;
    private int colum;
    private int Potition=-1;


    /**
     *
     * @param colum Tamaño de las columnas iniciando en 0
     */
    public void SetTamaño(int colum){
        this.colum=colum;
        Colum=new String[colum];

        //  SetNumeros(); //Establece implicitamente los Set
    }

    /**
     * Selecciona el dato que se enviara al siclo, este es continuo
     * @param dato i
     */
    public void SetN(String dato){
        Potition++;
        Colum[Potition]=dato;
    }

    /**
     * Devuelve el valor dependiendo de la pocicion asiganda de tu columna
     * @param I Slecion de el colum
     * @return
     */
    public String Get(int I){
        if (SetMaxVal(I)==true){
            return Colum[I];
        }
        else {
            return null;
        }
    }

    /**
     * No hay mas valores verificacion
     * @param valor columna
     * @return
     */
    private boolean SetMaxVal(int valor){
        if (valor>=colum){
            return false;
        }
        else {
            return true;
        }
    }



}