package com.example.eduadogurrola.yavax.CrearEvento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduadogurrola.yavax.AdapInicio.Item_add_evento.Citem_add_evento;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.eduadogurrola.yavax.R.id.Termino;

public class fechas_privacidad extends AppCompatActivity implements View.OnClickListener {


    Button Inicio,Sigui;
    AlertDialog dialog;
    Button HoraI,HoraT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechas_privacidad);

        //Declaracion
        Sigui=(Button) findViewById(R.id.siguiente);
        Inicio=(Button) findViewById(R.id.inicio);
        HoraI=(Button) findViewById(R.id.HoraI);
        HoraT=(Button) findViewById(R.id.HoraT);

        //Clic
        Sigui.setOnClickListener(this);
        Inicio.setOnClickListener(this);
        HoraI.setOnClickListener(this);
        HoraT.setOnClickListener(this);








    }


    String mes[]=new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio",
            "Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    String  fecha;
    /**
     * Creacion calendario
     */
    public void Calendario(){


        final AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        View view1=getLayoutInflater().inflate(R.layout.item_calendario,null);

        CalendarView calendarView=(CalendarView) view1.findViewById(R.id.calendario);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                  fecha=""+i+"-"+(i1+1)+"-"+i2;
                String fechagood="Dia:"+i2+" Mes:"+mes[i1];


                    Inicio.setText(""+fechagood);


                dialog.hide();
            }
        });

        dialog=alerta.create();
        dialog.setView(view1);
        dialog.show();



    }
    int che=0;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.siguiente:

                if (che>=3){
                    SharedPreferences guarda=getSharedPreferences("Evento",this.MODE_APPEND);
                    SharedPreferences.Editor cambiar=guarda.edit();

                    cambiar.putString("Dia",""+fecha);
                    cambiar.putString("HoraI",""+HoraI.getText());
                    cambiar.putString("HoraT",""+HoraT.getText());

                    cambiar.commit();
                    Intent Foto=new Intent(getApplicationContext(),Foto_evento.class);
                    startActivity(Foto);
                }
                else {
                    Toast.makeText(this, "Faltan Datos Importantes", Toast.LENGTH_SHORT).show();
                }



                break;
            case R.id.HoraI:
                che++;
                Horas(1);
                break;
            case R.id.HoraT:
                che++;
                Horas(2);
                break;
            case R.id.inicio:
                che++;
                Calendario();
                break;
        }
    }

    /**
     * Creacion Horas
     */
    public void Horas(final int tipo){


        final AlertaCreada  alertaCreada=new AlertaCreada(this);
        alertaCreada.CreaAleta(R.layout.item_horas,true,false,1);

        final  EditText Hor=alertaCreada.GetViewTools().findViewById(R.id.Horas);
        final  EditText Min=alertaCreada.GetViewTools().findViewById(R.id.Minutos);
        final  EditText Seg=alertaCreada.GetViewTools().findViewById(R.id.Segundos);
        Button Conf=alertaCreada.GetViewTools().findViewById(R.id.Confirmar);

        Conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Hor.getText().toString().isEmpty()){
                    Toast.makeText(fechas_privacidad.this, "Hay un error", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (tipo==1){
                        HoraI.setText(Hor.getText().toString()+":"+Min.getText().toString()+":"+Seg.getText().toString());
                        HoraI.setTextColor(getResources().getColor(R.color.blanco));
                    }
                    else {
                        HoraT.setText(Hor.getText().toString()+":"+Min.getText().toString()+":"+Seg.getText().toString());
                        HoraT.setTextColor(getResources().getColor(R.color.blanco));

                    }
                    alertaCreada.AlertHide();
                }

            }
        });



        //inicia eventos


    }
}
