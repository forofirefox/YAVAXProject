package com.example.eduadogurrola.yavax.Herramientas.Gibase;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Eduado Gurrola on 08/03/2018.
 */

public class MySingleTon {

private static MySingleTon mySingleTon;
private RequestQueue requestQueue;
private static Context mctx;
private MySingleTon(Context context){
        this.mctx=context;
        this.requestQueue=getRequestQueue();

        }
public RequestQueue getRequestQueue(){
        if (requestQueue==null){
        requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
        }
public static synchronized MySingleTon getInstance(Context context){
        if (mySingleTon==null){
        mySingleTon=new MySingleTon(context);
        }
        return mySingleTon;
        }
public<T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);

        }
        }
