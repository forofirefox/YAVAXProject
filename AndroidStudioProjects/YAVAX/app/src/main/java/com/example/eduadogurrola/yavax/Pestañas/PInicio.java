package com.example.eduadogurrola.yavax.Pestañas;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.eduadogurrola.yavax.AdapInicio.CarEstado.AdaptadorEstados;
import com.example.eduadogurrola.yavax.AdapInicio.CarRecuerdo.AdaptadorRecuerdo;
import com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos.BDevento;
import com.example.eduadogurrola.yavax.BaseDatos.BdRecuerdos.BDrecuer;
import com.example.eduadogurrola.yavax.R;

/**
 * Created by Eduado Gurrola on 07/02/2018.
 */

public class PInicio extends Fragment {


    /*Uso de las varibales de las listas*/
    private RecyclerView Lis,ListRecurdo;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    RelativeLayout Error;
    //Llenado de datos
    BDrecuer bDrecuer;
    BDevento bDevento;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fregmento_inicio, container, false);

        bDrecuer=new BDrecuer();
        bDevento=new BDevento();

        Error=view.findViewById(R.id.Error);
        Error.setVisibility(View.VISIBLE);

        SetListaEventos(view);  //Creacion list eventos

        SetListaRecuerdos(view);//Creacion lis recuerdo

        timer();


        return view;

    }

    /**
     * Creacion de la lista eventos
     * @param view
     */
    private void SetListaEventos(View view){
        //Creacion lista eventos
        Lis=(RecyclerView) view.findViewById(R.id.lista);
        Lis.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        Lis.setLayoutManager(layoutManager);
        //

        //llenado de la lista eventos
        adapter=new AdaptadorEstados(bDevento.SetAllRecuerdos(getContext()),getActivity());
        Lis.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //

    }

    /**
     * Creacion de la lista Recuerdos
     * @param view
     */
    private void SetListaRecuerdos(View view){
        //Creacion lista Rcuerdos
        ListRecurdo=(RecyclerView) view.findViewById(R.id.listrecuerdo);
        ListRecurdo.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager2=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        ListRecurdo.setLayoutManager(layoutManager2);//
        //LLenado de la apliacion


        adapter =new AdaptadorRecuerdo(bDrecuer.SetAllRecuerdos(getContext()),getActivity());
        ListRecurdo.setAdapter(adapter);


    }

    public void timer(){
        CountDownTimer countDownTimer=new CountDownTimer(1000*5,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(bDevento.Verificar()!=true){
                    timer();
                    Error.setVisibility(View.VISIBLE);
                }
                else {
                    adapter =new AdaptadorRecuerdo(bDrecuer.GetLista(getContext()),getActivity());
                    ListRecurdo.setAdapter(adapter);

                    if (bDevento.GetLista(getContext()).size()>0){
                        adapter=new AdaptadorEstados(bDevento.GetLista(getContext()),getActivity());
                        Lis.setAdapter(adapter);
                        Error.setVisibility(View.INVISIBLE);
                    }
                    else {
                        Error.setVisibility(View.VISIBLE);

                    }

                }

            }
        }.start();
    }

}
