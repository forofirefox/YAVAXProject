package com.example.eduadogurrola.yavax.Pestañas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos.BDevento;
import com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos.BDrecuer;
import com.example.eduadogurrola.yavax.Buscador.Volleybusqueda;
import com.example.eduadogurrola.yavax.R;

/**
 * Created by Eduado Gurrola on 26/04/2018.
 */

public class PAmigos extends Fragment {


    /*Uso de las varibales de las listas*/
    private RecyclerView Lis, ListRecurdo;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    EditText Buscar;

    RelativeLayout Error;
    //Llenado de datos
    BDrecuer bDrecuer;
    BDevento bDevento;

    Volleybusqueda volleybusqueda;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_amigos, container, false);

        volleybusqueda=new Volleybusqueda(getContext());
        bDrecuer = new BDrecuer();
        bDevento = new BDevento();

        Error = view.findViewById(R.id.Error);

        Buscar = view.findViewById(R.id.Buscar);

        Buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                volleybusqueda.MethodData(""+charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        return view;

    }
}