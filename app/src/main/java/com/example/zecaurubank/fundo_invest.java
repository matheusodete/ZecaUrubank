package com.example.zecaurubank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fundo_invest extends AppCompatActivity {

    private Button botaovoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundo_invest);
        getSupportActionBar().hide();

        botaovoltar=findViewById(R.id.voltar_fundoimo);

        botaovoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(fundo_invest.this,tela_invest.class);
                startActivity(intent);
            }
        });


    }
}