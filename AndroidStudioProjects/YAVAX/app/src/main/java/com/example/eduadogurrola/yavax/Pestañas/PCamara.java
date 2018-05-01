package com.example.eduadogurrola.yavax.Pestañas;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.eduadogurrola.yavax.CCamara.capturas;
import com.example.eduadogurrola.yavax.R;

import java.io.IOException;

import static java.security.AccessController.getContext;

/**
 * Created by Eduado Gurrola on 30/04/2018.
 */

public class PCamara extends Fragment implements SurfaceHolder.Callback{
        Camera camera;
        boolean changeph;
        Button cameraback;
        double surfacewidth = 1 ,surfaceheight = 1;
        Camera.PictureCallback jpegCall;
        int Camera_request = 911;
        SurfaceView mSurfaceView;
        SurfaceHolder mSurfaceHolder;
        Camera.Size size ;
        View view;
        int degrees = 0;
        Button btnpic,btnchange;
        int typeC = Camera.CameraInfo.CAMERA_FACING_BACK;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_frag_previw,container,false);
        wrotation();
        mSurfaceView = (SurfaceView) view.findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        PermaCam();
        Onevents();

        return view;
    }
    public void wrotation(){
        int rotation = getActivity().getWindowManager().getDefaultDisplay()
                .getRotation();

        switch (rotation) {
            case Surface.ROTATION_0: degrees = 90;  surfaceheight = 1.2; surfacewidth = 1.2; break;
            case Surface.ROTATION_90: degrees = 0; surfaceheight = 1.2; surfacewidth = 1.3; break;
            case Surface.ROTATION_180: degrees = 0; surfaceheight = 1.2; surfacewidth = 1.3;  break;
            case Surface.ROTATION_270: degrees = 180; surfaceheight = 1.2; surfacewidth = 1.3; break;
        }
        Toast.makeText(getContext(), ""+degrees, Toast.LENGTH_SHORT).show();

    }
    public void PermaCam(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.CAMERA},Camera_request);
        }
        else{

            mSurfaceHolder.addCallback(this);
            mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        }
    }

    public void Onevents(){
        btnpic = (Button)view.findViewById(R.id.shot);
        btnchange = (Button)view.findViewById(R.id.change);
        cameraback = (Button)view.findViewById(R.id.cameraback);
        cameraback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                changeph = !changeph;
                getActivity().recreate();




            }
        });
        btnpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapturePic();
            }
        });
        jpegCall = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Intent intent = new Intent(getActivity(),capturas.class);
                intent.putExtra("img",data);
                startActivity(intent);
                return;

            }
        };
    }
    public void CapturePic()
    {
        camera.takePicture(null,null,jpegCall);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        camera = Camera.open(typeC);
        size = getOptimalSize();
        Camera.Parameters parameters;
        parameters = camera.getParameters();
        camera.setDisplayOrientation(degrees);
        parameters.setPictureSize(size.width, size.height);
        parameters.setPreviewSize(size.width, size.height);
        parameters.setSceneMode(Camera.Parameters.SCENE_MODE_HDR);
        parameters.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);

        parameters.setAntibanding(Camera.Parameters.ANTIBANDING_60HZ);

        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        camera.setParameters(parameters);

        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            Log.e("error", "error en"+e.getMessage());
        }
        camera.startPreview();
        mSurfaceHolder = surfaceHolder;
    }
    /*private Camera.Size getOptimalResolution(){
        Camera.Size OptimalSize = null;
        List<Camera.Size>sizelist = camera.getParameters().getSupportedPreviewSizes();
        OptimalSize = sizelist.get(0);
        for (int i = 0; i < sizelist.size(); i++) {
            if ((sizelist.get(i).width * sizelist.get(i).height) > (OptimalSize.width * OptimalSize.height)) {
                OptimalSize = sizelist.get(i);
            }
        }
        return OptimalSize;
    }*/
    private Camera.Size getOptimalSize() {
        Camera.Size result = null;
        final Camera.Parameters parameters = camera.getParameters();

        for (final Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= mSurfaceView.getWidth() * surfacewidth && size.height <= mSurfaceView.getHeight() * surfaceheight) {
                if (result == null) {
                    result = size;
                } else {
                    final int resultArea = result.width * result.height;
                    final int newArea = size.width * size.height;

                    if (newArea > resultArea) {
                        result = size;
                    }
                }
            }
        }
        if (result == null) {
            result = parameters.getSupportedPreviewSizes().get(0);
        }
        return result;
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Camera_request){
            if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                mSurfaceHolder.addCallback(this);
                mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            }
            else
            {
                Toast.makeText(getContext(), "Permisos no adquiridos", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

