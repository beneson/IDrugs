package com.example.beneson.idrugs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.GeoPoint;

public class BuyActivity extends AppCompatActivity {

    protected Ofertado ofertado;
    final Activity atividade = this;
    protected int qtd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String idOfertado = intent.getStringExtra("idOfertado");

        Backendless.Persistence.of( Ofertado.class ).findById( idOfertado, new AsyncCallback<Ofertado>() {

            @Override
            public void handleResponse(final Ofertado ofertado) {

                if (ofertado==null){
                    Toast.makeText(atividade, "Oferta não encontrada. Favor pesquisar novamente.", Toast.LENGTH_LONG).show();
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onCreateContinue(ofertado);
                        }
                    });
                }

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.i("tagx", "Erro no Backendless: "+backendlessFault.toString());
                Toast.makeText(getApplicationContext(), "Problemas na Conexão. Verifique se há disponibilidade na rede.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreateContinue(Ofertado ofertado){

        TextView nomeProdutoBuy = (TextView) findViewById(R.id.nomeProdutoBuy);
        TextView nomeQuimicoBuy = (TextView) findViewById(R.id.nomeQuimicoBuy);
        TextView estabelecimentoBuy = (TextView) findViewById(R.id.estabelecimentoBuy);

        nomeProdutoBuy.setText(ofertado.getProduto().getNomeComercial());
        nomeQuimicoBuy.setText(ofertado.getProduto().getNomeQuimico());
        estabelecimentoBuy.setText(ofertado.getEstabelecimento().getNomeEstabelecimento());

        EditText qtdBuy = ((EditText) findViewById(R.id.qtdBuy));
        qtd = Integer.parseInt(qtdBuy.toString());

        this.ofertado = ofertado;

    }

    public void comprar(View v){


        if(ofertado.getQtdApresentacao() <= 0){
            Toast.makeText(atividade, "Estoque insuficiente. Favor buscar outro produto", Toast.LENGTH_LONG).show();
        }else{

            Pedido pedido = new Pedido();
            pedido.setOfertado(ofertado);
            pedido.setQtd(qtd);

            //verificarLogin();



        }

    }

    protected double calculaTempoEntrega(Estabelecimento estabelecimento, Usuario usuario){

        GeoPoint geoEstabelecimento = estabelecimento.getGeolocaliacao();
        GeoPoint geoUsuario = estabelecimento.getGeolocaliacao();

        return 0;
    }

}
