package com.example.pc2_desarrollo.mygps;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TtsSpan.TextBuilder messageTextView;
    private TtsSpan.TextBuilder messageTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener mlocListener = new MyLocationListener();
        mlocListener.setMainActivity(this);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,(LocationListener) mlocListener);
    }
    public class MyLocationListener implements LocationListener {
        MainActivity mainActivity;

        public MainActivity getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onLocationChanged(Location loc) {
            // Este método se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la detección de un cambio de ubicación
            loc.getLatitude();
            loc.getLongitude();
            String Text = "Mi ubicación actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            messageTextView.setText(Text);
            this.mainActivity.setLocation(loc);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onProviderDisabled(String provider) {
            // Este método se ejecuta cuando el GPS es desactivado
            messageTextView.setText("GPS Desactivado");
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onProviderEnabled(String provider) {
            // Este método se ejecuta cuando el GPS es activado
            messageTextView.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este método se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localización (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temporalmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setLocation(Location loc) {
        //Obtener la direcci—n de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    messageTextView2.setText("Mi dirección" +
                            "n es: \n" + address.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
