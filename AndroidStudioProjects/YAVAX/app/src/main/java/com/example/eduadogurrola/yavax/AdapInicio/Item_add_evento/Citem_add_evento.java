package com.example.eduadogurrola.yavax.AdapInicio.Item_add_evento;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.example.eduadogurrola.yavax.CrearEvento.crearEvento;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.R;

/**
 * Created by Eduado Gurrola on 15/02/2018.
 */

public class Citem_add_evento implements View.OnClickListener {
    private View view;
    private Context context;
    private AlertDialog dialog;
    private Button btn_alerta;
    private  Button CrearEvento;
    private Button btn_public;
    private AlertaCreada Hide;


    public Citem_add_evento(View view, Context context, AlertDialog dialog, AlertaCreada Hide){
        this.view=view;
        this.context=context;
        this.dialog=dialog;
        this.Hide=Hide;
        Parcear();
    }

    private void Parcear(){
        btn_alerta = (Button)view.findViewById(R.id.mini);
        CrearEvento = (Button)view.findViewById(R.id.CrearEvento);


        btn_alerta.setOnClickListener(this);
        CrearEvento.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.mini:
                btn_alerta.setEnabled(false);
                Hide.AlertHide();

                break;
            case R.id.CrearEvento:
                Intent Crear=new Intent(context, crearEvento.class);
                context.startActivity(Crear);

                break;
        }
    }
}
