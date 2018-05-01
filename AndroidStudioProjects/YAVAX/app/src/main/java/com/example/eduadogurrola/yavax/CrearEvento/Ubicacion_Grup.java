package com.example.eduadogurrola.yavax.CrearEvento;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eduadogurrola.yavax.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.N;
import static com.example.eduadogurrola.yavax.R.id.Nombre;

public class Ubicacion_Grup extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {


    private GoogleMap mMap;
    int checarmarca=1;
    private Marker marcador;
    double lat=0,lng=0;

    Button miub,zommor,zoomlen,Actual,confirmar;
    TextView direc;
    Location location;
    LocationManager locationManager;
    LatLng puntomarcado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion__grup);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    //declara
    direc=(TextView) findViewById(R.id.direccion);
    miub=(Button) findViewById(R.id.miubicacion);
    zommor=(Button) findViewById(R.id.zoomin);
    zoomlen=(Button) findViewById(R.id.zoomout);
    Actual=(Button) findViewById(R.id.actual);
    confirmar=(Button) findViewById(R.id.conf);


        miub.setOnClickListener(this);
        zoomlen.setOnClickListener(this);
        zommor.setOnClickListener(this);
        Actual.setOnClickListener(this);
        confirmar.setOnClickListener(this);







}
   /* public void cerrarteclado(){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(buscar.getWindowToken(), 0);
    }*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MiUbicacion();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                if (mMap!=null)
                    if (checarmarca==1){
                        setnombre(latLng);
                        puntomarcado=latLng;
                        lat = latLng.latitude;
                        lng = latLng.longitude;
                        marcador= mMap.addMarker(new MarkerOptions()
                                .position(latLng).title("Mi Lugar").icon(BitmapDescriptorFactory.fromResource(R.drawable.punto)));
                        Actual.setVisibility(View.VISIBLE);
                        checarmarca=0;
                    }
                    else {
                        marcador.remove();
                        setnombre(latLng);
                        puntomarcado=latLng;
                        lat = latLng.latitude;
                        lng = latLng.longitude;
                        marcador= mMap.addMarker(new MarkerOptions()
                                .position(latLng).title("Mi Lugar").icon(BitmapDescriptorFactory.fromResource(R.drawable.punto)));
                        Actual.setVisibility(View.VISIBLE);

                    }

            }
        });
    }
    public void setLocation(Location loc) {
        //Obtener la direcci—n de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    direc.setText("" + address.getAddressLine(0));
                    // locationManager.removeUpdates(locationListener);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setnombre(LatLng loc) {
        //Obtener la direcci—n de la calle a partir de la latitud y la longitud
        if (loc.latitude != 0.0 && loc.latitude != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.latitude, loc.longitude, 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    direc.setText("" + address.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        locationManager.removeUpdates(locationListener);

    }

    public void AgregarMarcador(double lat, double lng) {
        LatLng Punto = new LatLng(lat, lng);
        CameraUpdate MiUbicacion = CameraUpdateFactory.newLatLngZoom(Punto, 16);
        if (marcador != null)
            marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(Punto).title("Mi Posicion").icon(BitmapDescriptorFactory.fromResource(R.drawable.punto2)));
        mMap.moveCamera(MiUbicacion);

    }

    public void UbicacionActual(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            AgregarMarcador(lat, lng);


        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            UbicacionActual(location); setLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    public void Actualizando(){

    }

    private void MiUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        UbicacionActual(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1500,0,locationListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.miubicacion:
                MiUbicacion();
                Actual.setVisibility(View.INVISIBLE);


                break;
            case R.id.zoomin:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());

                break;
            case R.id.zoomout:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());

                break;
            case R.id.actual:
                CameraUpdate MiUbicacion = CameraUpdateFactory.newLatLngZoom(puntomarcado, 16);
                mMap.moveCamera(MiUbicacion);

                break;
            case R.id.conf:

                double[] vector=new double[2];

                SharedPreferences guarda=getSharedPreferences("Evento",this.MODE_APPEND);
                SharedPreferences.Editor cambiar=guarda.edit();

                cambiar.putString("Lat",""+lat);
                cambiar.putString("Lng",""+lng);

                cambiar.commit();

                Intent as=new Intent(getApplicationContext(),Cover.class);
                vector[0]=lat;
                vector[1]=lng;
                startActivity(as);
                recreate();
                break;
        }
    }
}
