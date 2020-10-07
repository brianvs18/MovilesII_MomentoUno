package com.example.momento_uno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.momento_uno.adapters.ClienteAdapter;
import com.example.momento_uno.models.ClienteModel;

public class RegistroActivity extends AppCompatActivity {

    private EditText edt_documento, edt_nombre;
    private Button btn_guardar;
    private ClienteAdapter clienteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        clienteAdapter = new ClienteAdapter(getApplicationContext());

        edt_documento = findViewById(R.id.edt_documento);
        edt_nombre = findViewById(R.id.edt_nombre);
        btn_guardar = findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String documento = edt_documento.getText().toString();
                String nombre = edt_nombre.getText().toString();

                if(documento.isEmpty() || nombre.isEmpty()){
                    Toast.makeText(RegistroActivity.this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    ClienteModel clienteModel = new ClienteModel(documento, nombre);
                    clienteAdapter.openW();
                    boolean insert = clienteAdapter.insert(clienteModel);
                    clienteAdapter.close();
                    if(insert){
                        Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    }else{
                        Toast.makeText(RegistroActivity.this, "Registro no exitoso", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void limpiarCampos(){
        edt_documento.setText("");
        edt_nombre.setText("");
        edt_documento.requestFocus();
    }
}