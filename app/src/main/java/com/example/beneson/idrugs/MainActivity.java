package com.example.beneson.idrugs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {

    EditText nomeMedicamento;
    Button botaoPesquisa;
    Intent intent;
    String stringMedicamento;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nomeMedicamento = (EditText) findViewById(R.id.nome_medicamento);
        botaoPesquisa = (Button) findViewById(R.id.botao_pesquisa);
    }

    protected void pesquisar(View v){
        try {
            callConnection();
        } catch (Exception e) {
            Log.i("tagx", "Erro na geolocalização");
            Toast.makeText(getApplicationContext(), "Problemas na localização. Verifique seu GPS.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private synchronized void callConnection() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        try {
            googleApiClient.connect();
        }catch(Exception e){
            Log.i("tagx", "Erro na geolocalização");
            Toast.makeText(getApplicationContext(), "Problemas na localização. Verifique seu GPS.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

        String endereco;
        Location localizacao;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("tagx", "Falha no GPS");
        }
        localizacao = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> local = null;

        try {
            local = geocoder.getFromLocation(localizacao.getLatitude(), localizacao.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (local != null && local.size() > 0)
            endereco = local.get(0).getAddressLine(0);
        else endereco = "Localização não encontrada";

        Log.i("tagx", "Latitude: " + localizacao.getLatitude() + " | Longitude: " + localizacao.getLongitude());
        Log.i("Tagx", endereco);

        stringMedicamento = nomeMedicamento.getText().toString();
        intent = new Intent(MainActivity.this, ListProductActivity.class);
        intent.putExtra("medicamento", stringMedicamento);
        intent.putExtra("endereco", "Lat: "+localizacao.getLatitude()+" | Long: "+localizacao.getLongitude()+" | "+endereco);

        startActivity(intent);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
