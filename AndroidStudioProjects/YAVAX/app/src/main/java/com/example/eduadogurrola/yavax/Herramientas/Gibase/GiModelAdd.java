package com.example.eduadogurrola.yavax.Herramientas.Gibase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Created by Eduado Gurrola on 08/03/2018.
 */

public class GiModelAdd {
    /*ALGORITMOJSON
    * Iniciamos el */
    //Columnas
    private int iColumnas = 0;

    //Renglones
    private int iRenglones = 0;

    LinkedList<ListaObjetoLibre> lista = new LinkedList<>();

    ListaObjetoLibre listaObjetoLibre;

    //Verifica si el arreglo ya esta comleto true=lleno false=faltan
    private boolean Activo = false;

    //Respuesta de el json
    private JSONArray response;

    //Respuesta del objeto json
    private JSONObject jsonObject;


    /**
     * Selecciona la respuesta de JSONArray
     * (Implisitamente iniciara el llenado de la lista con la magnitud que contiene la uri)
     *
     * @param Respuesta respuesta
     */
    public void SetJSONArray(JSONArray Respuesta) {

        try {

            jsonObject = Respuesta.getJSONObject(0); //Seleccion de el objeto

            this.iColumnas = jsonObject.names().length(); //seleccion de columnas
            this.iRenglones = Respuesta.length(); //Seleccion de renglones
            this.response = Respuesta; //Seleccion de la espuesta para leer los datos


            GetDatos(); //Inicamos el llenado de los datos

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * Seleccion una columna
     */
    public void SetJSONArray(JSONObject Respuesta) {

            this.iColumnas=1;
            this.iRenglones = Respuesta.length(); //Seleccion de renglones
            GetDatos(Respuesta); //Inicamos el llenado de los datos
    }

    /**
     * Selecciona la respuesta de JSONArray
     * (Implisitamente iniciara el llenado de la lista con la magnitud que contiene la uri)
     * @param Respuesta respuesta
     */
    public void SetJSONObjet(JSONArray Respuesta){

        try {

            jsonObject = Respuesta.getJSONObject(0); //Seleccion de el objeto

            this.iColumnas=jsonObject.names().length(); //seleccion de columnas
            this.iRenglones=Respuesta.length(); //Seleccion de renglones
            this.response=Respuesta; //Seleccion de la espuesta para leer los datos

            GetDatos(); //Inicamos el llenado de los datos

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * Selecciona el tamaño del tamaño de mis columnas en la
     * lista de objetos
     */
    private void SetTamañoLista(){

        listaObjetoLibre= new ListaObjetoLibre(); //Iniciamos la lista de objetos con el tamaño de las columans

        listaObjetoLibre.SetTamaño(iColumnas); //Seleccionamos el tamaño de las columanas

    }

    /**
     * Iniciamos el llenado de los datos en json
     */
    private void GetDatos(){

        SetValidado(); //Revisamos el arreglo si tiene los datos solicitados

        if (Activo==false){

            for (int i = 0; i < iRenglones; i++) {

                try {
                    jsonObject=response.getJSONObject(i); //Leemos por renglon


                    SetTamañoLista(); //Inicia la lista de objetos

                    for (int j = 0; j < iColumnas; j++) { //Iniciamos el conteo de columnas para llenar dato por dato

                        String key=""+jsonObject.names().get(j); //Sleccionamos el nombre y lo enviamos

                        String Dato= jsonObject.getString(key); //Declaramos el dato junto con su key

                        listaObjetoLibre.SetN(Dato); //Introducimos el dato en la columna j

                    }
                    lista.add(listaObjetoLibre);


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * Iniciamos el llenado de los datos en json
     */
    private void GetDatos(JSONObject Respuesta){

        SetValidado(); //Revisamos el arreglo si tiene los datos solicitados

        if (Activo==false){

            for (int i = 0; i < iRenglones; i++) {

                try {
                    SetTamañoLista(); //Inicia la lista de objetos

                    for (int j = 0; j < iColumnas; j++) { //Iniciamos el conteo de columnas para llenar dato por dato

                        String Dato= Respuesta.getString((String)Respuesta.names().get(0)); //Declaramos el dato junto con su key

                        listaObjetoLibre.SetN(Dato); //Introducimos el dato en la columna j

                    }
                    lista.add(listaObjetoLibre);


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * Retornala lista con los datos insertados
     * @return
     */
    public LinkedList<ListaObjetoLibre> GetLista(){
        return lista;
    }


    /**
     * Verifica el tamaño de el objeto
     */
    private void SetValidado(){
        if(lista.size()==iRenglones) {
            Activo = true;
        }
    }
}
