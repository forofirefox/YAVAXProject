package com.example.eduadogurrola.yavax.CCamara;

/**
 * Created by Eduado Gurrola on 25/04/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * Created by user on 23/04/2018.
 */

public class volleysend implements Response.ErrorListener,Response.Listener<JSONObject> {
    JSONObject fotos;
    Context context;
    RequestQueue VRequest;
    String url = "http://192.168.173.1:8080/upimg";
    Bitmap bitmap;
    public volleysend(Context context,Bitmap bitmap) {
        this.context = context;
        this.bitmap  = bitmap;
        VRequest = Volley.newRequestQueue(context);
    }

    public void MethodData(String Comentario){
        String b64 = ConvertToString(bitmap);
        Random x=new Random();
        fotos = new JSONObject();
        SharedPreferences guarda=context.getSharedPreferences("Evento",context.MODE_APPEND);
        int Id=guarda.getInt("Id_",0);
        int id_evento=guarda.getInt("Id_evento",Id);


        try {
            fotos.put("b64img",b64);
            fotos.put("color",x.nextInt(5));
            fotos.put("fkpersona",Id);
            fotos.put("fkevento",id_evento);
            fotos.put("comentario",Comentario);

            Toast.makeText(context, ""+fotos.length(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MethodRequest();
    }
    public void MethodRequest(){
        JsonObjectRequest request = new JsonObjectRequest(url,fotos,this,this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VRequest.add(request);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (response.getInt("respuesta")==1){
                Toast.makeText(context, "correcto", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Unete a un grupo", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String ConvertToString(Bitmap bitmap)
    {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,60,array);
        byte[] imagenByte = array.toByteArray();
        Toast.makeText(context, ""+imagenByte[9], Toast.LENGTH_SHORT).show();
        String imagenstring = Base64.encodeToString(imagenByte,Base64.DEFAULT);
        return imagenstring;
    }
}

