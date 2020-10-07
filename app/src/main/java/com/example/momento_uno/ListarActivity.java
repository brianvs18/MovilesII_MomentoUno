package com.example.momento_uno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.momento_uno.adapters.ClienteAdapter;
import com.example.momento_uno.models.ClienteModel;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {

    private TextView tv_listar_contenido;
    private ClienteAdapter clienteAdapter;
    private ArrayList<ClienteModel> clienteModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        clienteAdapter = new ClienteAdapter(getApplicationContext());

        tv_listar_contenido = findViewById(R.id.tv_listar_contenido);

        clienteAdapter.openR();
        clienteModels = clienteAdapter.selectAll();
        clienteAdapter.close();

        String lista_recorrido = "";
        for(ClienteModel clienteModel : clienteModels){
            lista_recorrido +=
                "ID: " + clienteModel.get_id() +
                ".\nDocumento: " + clienteModel.get_documento() +
                ".\nNombre: " + clienteModel.get_nombre() +
                ".\n---------------------------------------\n";
        }
        tv_listar_contenido.setText(lista_recorrido);
    }
}