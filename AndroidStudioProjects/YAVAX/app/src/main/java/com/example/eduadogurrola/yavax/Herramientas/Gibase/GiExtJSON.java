package com.example.eduadogurrola.yavax.Herramientas.Gibase;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Created by Eduado Gurrola on 13/03/2018.
 */

public class GiExtJSON {


    /*Creacion de los methodos de la clase extendida GiRespuestaJson*/

    GiModelAdd giModelAdd=new GiModelAdd();

    private Context context;

    private String Url;


    /**
     * Creacion de el tipo de methodo te retorna una lista
     * @param Methodo selecct methodo
     * @param context contexto
     * @param Url url a la cual viajara
     * @param iTipo tipo array objet string
     */
    protected void SetMethod(int Methodo, Context context, String Url, int iTipo){
        this.context=context;
        this.Url=Url;

        for (int i = 0; i < 8 ; i++) {
            if ((Methodo-1)==i){
                SetTipo(iTipo);
                break;
            }
        }


    }

    /**
     * Verificacion del tipo de navegacion
     * @param iTip 0 Objet 1 Array
     */
    private void SetTipo(int iTip){
        if (iTip==0){
            SetDatosObjet();
        }
        else {
            SetUriDatos();
        }
    }

    /**
     * Intercepta datos Array
     */
    private void SetUriDatos(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        giModelAdd.SetJSONArray(response); //lamamos el el algoritmo SetJson
                        Toast.makeText(context, "Entro", Toast.LENGTH_SHORT).show();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No hay conexion" + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }
        );
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(1000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleTon.getInstance(context).addToRequestQue(jsonArrayRequest);


    }

    /**
     *Intercepta datos de un objeto
     */
    private void SetDatosObjet(){


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    if (response.length()==1){
                        giModelAdd.SetJSONArray(response);
                        Toast.makeText(context,"Entro",Toast.LENGTH_LONG).show();
                    }
                    else {
                        JSONArray array=response.getJSONArray(""+response.names().get(0)); //Leemos el objeto
                        giModelAdd.SetJSONArray(array); //Creamos columnas y renglones
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No hay conexion " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleTon.getInstance(context).addToRequestQue(jsonObjectRequest);

    }

    /**
     * Retorna lista libre
     * @return lista
     */
    public LinkedList<ListaObjetoLibre> GetLista(){

        return giModelAdd.GetLista();
    }



}
