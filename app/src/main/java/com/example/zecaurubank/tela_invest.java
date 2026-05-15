package com.example.zecaurubank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class tela_invest extends AppCompatActivity {

    private Button botaoV;
    private Button botaoF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_invest);
        getSupportActionBar().hide();

        botaoF = findViewById(R.id.bt_fundoimobiliario);

        botaoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela_invest.this,fundo_invest.class);
                startActivity(intent);
            }
        });


        botaoV = findViewById(R.id.bt_voltar_invest);

        botaoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela_invest.this,tela_principal.class);
                startActivity(intent);
            }
        });
    }
}