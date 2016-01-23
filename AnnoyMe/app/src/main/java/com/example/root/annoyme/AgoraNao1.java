package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by gabriela on 21/01/16.
 */
public class AgoraNao1 extends AppCompatActivity implements OnMapReadyCallback{

    private Double latitude, longitude;
    private ArrayList<String> listaRespostas,respondidas;
    private Button btnEnviar;
    private TextView text;
    private Dados dados;
    private AlertDialog.Builder alert;
    private Context context;
    private String resposta, ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agoranao1);
        dados = new Dados();
        context = this;
        listaRespostas = new ArrayList<String>();
        ////System.out.println(getIntent().getStringExtra("respostas"));
        resposta = getIntent().getStringExtra("respostas");

        respondidas = getIntent().getStringArrayListExtra("respondidas");

        ////System.out.println(resposta);
        ////System.out.println(resposta.indexOf("pendente,"));
        resposta = resposta.replace("pendente,", "ok,");
        respondidas.add(resposta);
        resposta = resposta.replace("ok,", "");

        ////System.out.println(resposta.indexOf('('));
        ////System.out.println(resposta.indexOf(')'));
        ll = resposta.substring(resposta.indexOf("(") + 1, resposta.indexOf(")"));
        ////System.out.println(ll);


        latitude = Double.parseDouble(ll.substring(0, ll.indexOf(",")-1));
        longitude = Double.parseDouble(ll.substring(ll.indexOf(",")+1));

        ////System.out.println(latitude);
        ////System.out.println(longitude);

        resposta = resposta.replace("(,", "");
        resposta = resposta.replace("),", "");

        listaRespostas.add(resposta);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);



        text = (TextView) findViewById(R.id.agoraNao1_horario);

        String horario;

        //System.out.println(listaRespostas.get(0));
        //System.out.println(listaRespostas.get(0).indexOf("_"));
        //System.out.println(listaRespostas.get(0).indexOf(","));
        horario = listaRespostas.get(0).substring(listaRespostas.get(0).indexOf("_") + 1, listaRespostas.get(0).indexOf(","));
        horario = horario.replace("-", "h");
        text.setText(horario);
        //System.out.println(horario);


        /********** Salvar as informações **********/
        btnEnviar = (Button) findViewById(R.id.button_agoraNao1);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = "";
                texto = ((EditText) findViewById(R.id.agoraNao_text)).getText().toString();


                if (texto.length() > 0) {
                    listaRespostas.add(texto);

                    SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");

                    listaRespostas.add(simpleFormat.format(new Date(System.currentTimeMillis())));

                    if (dados.salvarDados(listaRespostas, "agoraN")) {

                        dados.verificarArquivoPendentes(1, respondidas);

                        alert = new AlertDialog.Builder(context);
                        alert.setTitle("Concluído.");
                        alert.setMessage("Questionário finalizado com sucesso!").setCancelable(false).setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent nextActivity = new Intent(AgoraNao1.this, MainActivity.class);
                                        startActivity(nextActivity);
                                        finish();
                                    }
                                });
                        alert.create().show();

                    } else {

                        alert = new AlertDialog.Builder(context);
                        alert.setTitle("ERRO");
                        alert.setPositiveButton("Ok", null);
                        alert.setMessage("Erro em salvar o formulário.");
                        alert.create().show();

                    }
                } else {
                    dados.exibeDialogo("Todas as questões devem ser respondidas.", context);
                }


            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Seu lugar"));

        CameraPosition camPos = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(17)
                .build();
        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        googleMap.animateCamera(camUpd3);

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Atenção");

        alertDialogBuilder.setMessage("Não é possível voltar para a janela anterior.").setCancelable(false).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}