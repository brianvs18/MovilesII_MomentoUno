package com.example.momento_uno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.momento_uno.adapters.ClienteAdapter;
import com.example.momento_uno.models.ClienteModel;

import java.util.ArrayList;

public class ListaClienteActivity extends AppCompatActivity {

    private ClienteAdapter clienteAdapter;
    private ArrayList<ClienteModel> clienteModels;
    private ListView lv_listarClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        clienteAdapter = new ClienteAdapter(getApplicationContext());
        lv_listarClientes = findViewById(R.id.lv_listarClientes);
        clienteModels = new ArrayList<>();

        clienteAdapter.openR();
        clienteModels = clienteAdapter.selectAll();
        clienteAdapter.close();

        lv_listarClientes.setAdapter(new ClienteAdapter(this, clienteModels));
    }
}