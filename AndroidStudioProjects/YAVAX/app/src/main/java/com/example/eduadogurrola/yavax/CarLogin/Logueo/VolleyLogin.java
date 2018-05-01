package com.example.eduadogurrola.yavax.CarLogin.Logueo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eduadogurrola.yavax.R.id.HoraI;
import static com.example.eduadogurrola.yavax.R.id.HoraT;

/**
 * Created by Eduado Gurrola on 25/04/2018.
 */

public class VolleyLogin implements Response.ErrorListener,Response.Listener<JSONObject>{
    //variables
    Context context;
    View view;
    String vista = "http://192.168.173.1:8080/mensaje";
    RequestQueue VRequest;
    String usuario;
    String contra;
    VolleyLogin.ListenerLogin listenerPacket;


    //constructor
    public VolleyLogin(Context context) {
        this.context = context;
        VRequest = Volley.newRequestQueue(context);
    }

    /**
     *
     * @param listenerPacket Este es el escuchador del login para saber si estuvo correcto o no
     */
    public void onListenerPacket(VolleyLogin.ListenerLogin listenerPacket){
        this.listenerPacket = listenerPacket;
    }

    public void Method(String nombre,String contra){

        JSONObject cert = new JSONObject();
        try {
            cert.put("usuario",nombre);
            cert.put("contra",contra);
        }catch (JSONException e)
        {
            Log.e("error", e.getMessage());
        }

        JsonObjectRequest jsonArequest = new JsonObjectRequest(vista,cert,this,this);
        VRequest.add(jsonArequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
        listenerPacket.onListenerLogin(false);

    }
    @Override
    public void onResponse(JSONObject response) {
        try {

            if (response.getInt("correcto") >= 1) {

                SharedPreferences guarda=context.getSharedPreferences("Evento",context.MODE_APPEND);
                SharedPreferences.Editor cambiar=guarda.edit();

                cambiar.putInt("Id_",+response.getInt("correcto"));

                cambiar.commit();
                listenerPacket.onListenerLogin(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public  interface ListenerLogin{
        void onListenerLogin(boolean estado);
    }
}
