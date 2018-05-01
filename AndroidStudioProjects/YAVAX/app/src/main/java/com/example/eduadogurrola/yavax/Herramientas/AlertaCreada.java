package com.example.eduadogurrola.yavax.Herramientas;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.eduadogurrola.yavax.R;

/**
 * Created by Eduado Gurrola on 14/02/2018.
 */

public class AlertaCreada {
    /**
     * Clase diseñada para crear alertas con un estilo llamativo para el usuario
     * Toma los pasos siguientes:
     * 1.- Llamar la clase.
     * 2.- Inicia el crearAlerta que te pedira los atrivutos necesarios
     * 3.- Para finalizar tu alerta es necesario que utilizes el metodo getDialog
     */

   private AlertDialog dialog;
   private Context context;
    private Animation slider_in;
    private Animation slider_out;
   private int Layout;
    private boolean Trasparecia;
    private boolean FullScreem;
    private int TipoAnimacion;
    /***
     * View_parameters: Se usa para el retorno de la view
     * IDS: se usa para poder
     */
    private View View_parameters;


    /**
     * Inicialisa la clase para poder crear tu alerta
     * @param context Ubicacion de tu actividad
     */
    public AlertaCreada(Context context){
        this.context=context;
        slider_in= AnimationUtils.loadAnimation(context, R.anim.slider_on);
        slider_out= AnimationUtils.loadAnimation(context,R.anim.slider_out);
    }


    /**
     * Crea tu alerta a con un estilo mas alla de lo que te imaginas
     * con las herramientas que te proporciona el plugin.
     *
     * @param Layout Ubicación de nuestro layout R.layout.N
     * @param Trasparecia Selección de transparencia True o False
     * @param FullScreem Seleccion de FullScreen True o False
     * @param TipoAnimacion Seleccion tipo de animacion 1 Slider_out
     */
    public void CreaAleta(@NonNull int Layout, boolean Trasparecia, boolean FullScreem, @NonNull int TipoAnimacion){
        this.Layout=Layout;
        this.Trasparecia=Trasparecia;
        this.FullScreem=FullScreem;
        this.TipoAnimacion=TipoAnimacion;

        addEstilo();

    }

    /**
     *Selecciona la animacion.
     * @param tipo  Tipo de la animacion Crear mas si es necesario
     * @param layout vista que tomara el staranimation
     */
    private void SetAnimation(@NonNull int tipo,View layout){
        switch (tipo){
            case 1:
                layout.startAnimation(slider_in);
                break;
            case 2:
                layout.startAnimation(slider_out);

                break;
            default:

        }
    }


    private ColorDrawable colorDrawable;
    private int Tema;

    /**
     * Inicia el estilo de el Alerta Tomara el fullscreem para verificar
     * como se quiere el alerta.
     */
    private void addEstilo(){
        if (FullScreem==true){
             Tema=android.R.style.Theme_Black_NoTitleBar_Fullscreen;
        }
        GetAlertaLayout();

    }

    /**
     * Inicia el aertaBuilder con el tema especificado
     * seleccionando la animacion y invocando el dialog
     * @return
     */
    private Object GetAlertaLayout(){
        AlertDialog.Builder inicia=new AlertDialog.Builder(context,Tema);
        View layout = LayoutInflater.from(context).inflate(Layout,null);


        SetAnimation(TipoAnimacion,layout);

        View_parameters=layout;
        GetAlertaDialog(inicia,layout);

        return null;
    }

    /**
     * Inicia el dialgo para poder finalizar o inicaiar.
     * @param inicia AlertaBuilder con el nombre Inicia
     * @param Layout la vista para poder manipularla
     * @return
     */
    private Object GetAlertaDialog(AlertDialog.Builder inicia,View Layout){
        dialog=inicia.create();
        if (Trasparecia!=false){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        dialog.setView(Layout);
        dialog.show();
        return null;
    }

    /**
     * Retorna El dialog con el cual podremos finalizar el
     * dialogo
     * @return
     */
    public AlertDialog GetDialog(){
        return dialog;
    }

    /**
     * Retorna la view para poder agregar Eventos A su gusto
     * @return
     */
    public View GetViewTools(){
        return View_parameters;
    }

    /**
     * Esconde la alerta
     * @return
     */
    public int AlertHide(){
        SetAnimation(2,View_parameters);
        Timer();
        return 0;
    }

    /**
     * Timer sobre tiempo 300
     */
    private void Timer(){
        CountDownTimer countDownTimer=new CountDownTimer(300,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                dialog.dismiss();
            }
        }.start();
    }

}
