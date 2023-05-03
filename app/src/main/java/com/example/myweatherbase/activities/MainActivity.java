package com.example.myweatherbase.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity implements CallInterface {

    private TextView txtView, textViewDay, textFecha, textDay, textTempAct, textTempMax, textTempMin ;
    private ImageView imageView;
    private Root root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.txtView);
        textViewDay = findViewById(R.id.textViewDay);
        txtView = findViewById(R.id.txtView);
        imageView = findViewById(R.id.imageView);
        textDay = findViewById(R.id.textDay);
        textFecha = findViewById(R.id.textFecha);
        textTempAct = findViewById(R.id.textTempAct);
        textTempMax = findViewById(R.id.textTempMax);
        textTempMin = findViewById(R.id.textTempMin);
        // Mostramos la barra de progreso y ejecutamos la llamada a la API
        showProgress();
        executeCall(this);
    }

    // Realizamos la llamada y recogemos los datos en un objeto Root
    @Override
    public void doInBackground() {
        root = Connector.getConector().get(Root.class,"&lat=39.4913900&lon=-0.4634900");
    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    @Override
    public void doInUI() {
        hideProgress();
        txtView.setText(root.list.get(0).weather.get(0).description);
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + root.list.get(0).weather.get(0).icon + Parameters.ICON_URL_POST, imageView);

        Date date = new Date((long)root.list.get(0).dt*1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("EEEE");
        SimpleDateFormat dateComplete = new SimpleDateFormat("d/MMMM/yyyy");
        SimpleDateFormat dateHour = new SimpleDateFormat("HH:mm"); // "EEE, d MMM yyyy HH:mm"
        textTempAct.setText("Temp: " +root.list.get(0).main.temp+"ºC");
        textTempMax.setText("TempMax: " +root.list.get(0).main.temp_max+"ºC");
        textTempMin.setText("TempMin: " +root.list.get(0).main.temp_min+"ºC");

        textDay.setText(dateDayOfWeek.format(date));
        textFecha.setText(dateComplete.format(date));
        textViewDay.setText(dateHour.format(date));
    }
}