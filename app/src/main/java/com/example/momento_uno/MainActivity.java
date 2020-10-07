package com.example.momento_uno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_main_registrarse;
    private Button btn_main_listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_registrarse = findViewById(R.id.btn_main_registrarse);
        btn_main_listar = findViewById(R.id.btn_main_listar);

        btn_main_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(registro);
            }
        });

        btn_main_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listar = new Intent(MainActivity.this, ListaClienteActivity.class);
                startActivity(listar);
            }
        });
    }
}