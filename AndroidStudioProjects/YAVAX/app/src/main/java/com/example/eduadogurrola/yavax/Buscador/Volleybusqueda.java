package com.example.eduadogurrola.yavax.Buscador;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.eduadogurrola.yavax.Herramientas.Gibase.GiModelAdd;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eduado Gurrola on 30/04/2018.
 */

public class Volleybusqueda implements Response.ErrorListener,Response.Listener<JSONObject> {
    JSONObject busqueda;
    Context context;
    RequestQueue VRequest;
    String url = "http://192.168.173.1:8080/buscador";
    Bitmap bitmap;

    GiModelAdd giModelAdd=new GiModelAdd();


    public Volleybusqueda(Context context) {
        this.context = context;

        VRequest = Volley.newRequestQueue(context);
    }

    public void MethodData(String txtbusq){


        busqueda = new JSONObject();
        try {
            busqueda.put("busqueda",txtbusq);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        MethodRequest();
    }
    public void MethodRequest(){
        JsonObjectRequest request = new JsonObjectRequest(url,busqueda,this,this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VRequest.add(request);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        giModelAdd.SetJSONArray(response);
        Lista();
    }

    public void Lista(){
        if (giModelAdd.GetLista().size()>0){
            Toast.makeText(context, ""+giModelAdd.GetLista().get(0).Get(0), Toast.LENGTH_SHORT).show();
        }
    }
}
