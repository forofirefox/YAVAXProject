
package com.example.eduadogurrola.yavax;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduadogurrola.yavax.AdapInicio.Item_add_evento.Citem_add_evento;
import com.example.eduadogurrola.yavax.CCamara.Camara_vista;
import com.example.eduadogurrola.yavax.Herramientas.AlertaCreada;
import com.example.eduadogurrola.yavax.Herramientas.List_Recuerdo.DimencionPantalla;
import com.example.eduadogurrola.yavax.PerfilUsuario.perfil;
import com.example.eduadogurrola.yavax.Pestañas.Pegga;

public class  Navegador extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TextView Titu,SubTitu,Nam;
    private Typeface scrip,scripDos;
    private TabLayout tab;
    private ViewPager viewPager;
    private Button CrearEvento,Camara,Bperfil;

    DimencionPantalla dimencionPantalla;
    AlertaCreada  alertaCreada;
    private Citem_add_evento citem_add_evento;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);
        Bundle x=getIntent().getExtras();

        dimencionPantalla=new DimencionPantalla(this);

        Toast.makeText(this, ""+dimencionPantalla.getHeigth(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+dimencionPantalla.getWidth(), Toast.LENGTH_SHORT).show();

        tab=(TabLayout) findViewById(R.id.tab);
        viewPager=(ViewPager) findViewById(R.id.pegar);
        CrearEvento=(Button) findViewById(R.id.CrearGrupo);
        Camara=(Button) findViewById(R.id.Camara);
        Bperfil=(Button) findViewById(R.id.perfil);

        CrearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertaCreada=new AlertaCreada(Navegador.this);
                alertaCreada.CreaAleta(R.layout.item_add_evento,true,true,1);
                citem_add_evento=new Citem_add_evento(alertaCreada.GetViewTools(),Navegador.this,alertaCreada.GetDialog(),
                        alertaCreada);

            }
        });
        Camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ca=new Intent(getBaseContext(),Camara_vista.class);
                startActivity(ca);

            }
        });
        Bperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent per=new Intent(getBaseContext(),perfil.class);
                startActivity(per);

            }
        });


        //tipodefuente
        String Fuente="Fuente/DKGrigory.otf";
        String Fuente2="Fuente/StiloPro.ttf";

        this.scrip=Typeface.createFromAsset(getAssets(),Fuente);
        this.scripDos=Typeface.createFromAsset(getAssets(),Fuente);




        tab.addTab(tab.newTab().setIcon(R.drawable.tapicon1));
        tab.addTab(tab.newTab().setIcon(R.drawable.tapicon2));
        tab.addTab(tab.newTab().setIcon(R.drawable.tapicon3));
        tab.setSelectedTabIndicatorHeight(1);
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.Cazul));
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));


        Pegga pegga=new Pegga(getSupportFragmentManager(),tab.getTabCount());
        viewPager.setAdapter(pegga);

        tab.setOnTabSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(alertaCreada!=null){
            alertaCreada.AlertHide();
        }
        else{
            onPause();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }



}
