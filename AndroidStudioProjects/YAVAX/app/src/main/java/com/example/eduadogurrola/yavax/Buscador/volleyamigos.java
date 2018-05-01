package com.example.eduadogurrola.yavax.Buscador;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eduado Gurrola on 30/04/2018.
 */

public class volleyamigos implements Response.ErrorListener,Response.Listener<JSONObject> {
    JSONObject amigos;
    Context context;
    RequestQueue VRequest;
    String url ;
    int c1;
    ListenerAmigo listenerAmigo;
    public volleyamigos(Context context) {
        this.context = context;
        VRequest = Volley.newRequestQueue(context);
    }
    public void onListenerAmigo(volleyamigos.ListenerAmigo listenerPacket){
        this.listenerAmigo = listenerPacket;
    }
    public void MethodData(int IdM,int IdP){
        url = "http://192.168.173.1/amigoscheck";
        c1=1;
        amigos = new JSONObject();
        try {
            amigos.put("IdM",IdM);
            amigos.put("IdP",IdP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MethodRequest();
    }
    public void MethodDataelimnar(int IdM,int IdP){
        url = "http://192.168.173.1/amigosborro";
        c1=2;
        amigos = new JSONObject();
        try {
            amigos.put("IdM",IdM);
            amigos.put("IdP",IdP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MethodRequest();
    }
    public void MethodDataagregar(int IdM,int IdP){
        url = "http://192.168.173.1/amigosagrego";
        c1=3;
        amigos = new JSONObject();
        try {
            amigos.put("IdM",IdM);
            amigos.put("IdP",IdP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MethodRequest();
    }
    public void MethodDataaceptar(int IdM,int IdP){
        url = "http://192.168.173.1/amigosacepto";
        c1=4;
        amigos = new JSONObject();
        try {
            amigos.put("IdM",IdM);
            amigos.put("IdP",IdP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MethodRequest();
    }
    public void MethodRequest(){
        JsonObjectRequest request = new JsonObjectRequest(url,amigos,this,this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VRequest.add(request);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        if (c1 == 1) {
            tipo1(response);
        }
        else if (c1 == 2){
            Toast.makeText(context, "correcto elimino", Toast.LENGTH_SHORT).show();
        }
        else if (c1 == 3){
            Toast.makeText(context, "correcto agrego", Toast.LENGTH_SHORT).show();
        }
        else if (c1 == 4){
            Toast.makeText(context, "correcto aceptar", Toast.LENGTH_SHORT).show();
        }
    }
    public void tipo1(JSONObject response){
        try{
            if (response.getInt("tipo")==0)
            {
                listenerAmigo.onListenerAmigo(0);
            }
            else if (response.getInt("tipo")==1){
                listenerAmigo.onListenerAmigo(1);

            }
            else {
                listenerAmigo.onListenerAmigo(911);

            }

        }catch (JSONException e){
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public  interface ListenerAmigo{
        void onListenerAmigo(int estado);
    }
}
