package com.example.eduadogurrola.yavax.CCamara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.eduadogurrola.yavax.Navegador;
import com.example.eduadogurrola.yavax.R;

public class capturas  extends AppCompatActivity {
    Button btn,btnback;
    EditText publi;
    int degrees = 0;
    Bitmap rotatebm;
    volleysend vs;
    Save save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturas);

        Bundle extra = getIntent().getExtras();
        assert extra != null;

        byte[] data = extra.getByteArray("img");
        if (data != null){
            ImageView image = (ImageView)findViewById(R.id.imgcaptured);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
            rotatebm = rotation(bitmap);
            image.setImageBitmap(rotatebm);
            vs = new volleysend(getApplicationContext(),rotatebm);
            action();
            save = new Save();
        }
    }
    public Bitmap rotation(Bitmap imagecode){
        int w = imagecode.getWidth();
        int h = imagecode.getHeight();

        Matrix matrix =  new Matrix();
        matrix.setRotate(90);

        return Bitmap.createBitmap(imagecode,0,0,w,h,matrix,true);
    }
    public void action(){
        btn = (Button)findViewById(R.id.send);
        publi = (EditText)findViewById(R.id.publicacion);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save.SaveImage(getApplicationContext(),rotatebm);
                vs.MethodData(""+publi.getText());
                Intent Nav=new Intent(getBaseContext(), Navegador.class);
                startActivity(Nav);
                finish();
            }
        });

    }

}

