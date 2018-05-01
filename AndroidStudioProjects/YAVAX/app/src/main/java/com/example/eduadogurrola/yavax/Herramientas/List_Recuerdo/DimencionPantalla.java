package com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by Eduado Gurrola on 11/04/2018.
 */

public class DimencionPantalla {

    Context context;
    private int width;
    private int heigth;

    public DimencionPantalla(Context context){
        this.context=context;
         width = context.getResources().getConfiguration().screenWidthDp;// ancho absoluto en pixels
         heigth = context.getResources().getConfiguration().screenHeightDp;// alto absoluto en pixels

    }

    /**
     * ancho absoluto en pixels
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * alto absoluto en pixels
     * @return
     */
    public int getHeigth() {
        return heigth;
    }
}
