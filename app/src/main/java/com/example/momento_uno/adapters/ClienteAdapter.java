package com.example.momento_uno.adapters;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.momento_uno.R;
import com.example.momento_uno.database.DataBaseHelper;

import com.example.momento_uno.models.ClienteModel;

import java.util.ArrayList;

public class ClienteAdapter  extends BaseAdapter {
    private static final String BASE_DE_DATOS = "registrodb.db";
    private static final int VERSION = 4;
    public static final String SCRIPT_CREAR_TABLA = "create table cliente (id integer primary key autoincrement, documento text, nombre text)";
    public static final String SCRIPT_BORRAR_TABLA = "drop table if exists cliente";
    private static final String NOMBRE_TABLA = "cliente";


    private static SQLiteDatabase database;
    private static DataBaseHelper dataBaseHelper;

    private final Context context;
    private ClienteModel clienteModel;
    private ArrayList<ClienteModel> clienteModels;

    public ClienteAdapter(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context, BASE_DE_DATOS, null, VERSION);
    }

    public ClienteAdapter(Context context, ArrayList<ClienteModel> clienteModels) {
        this.context = context;
        this.clienteModels = clienteModels;
    }

    public void openW(){
        database = dataBaseHelper.getWritableDatabase();
    }
    public void openR(){
        database = dataBaseHelper.getReadableDatabase();
    }
    public void close(){
        database.close();
    }

    public boolean insert(ClienteModel clienteModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("documento", clienteModel.get_documento());
        contentValues.put("nombre", clienteModel.get_nombre());

        long result = database.insert(NOMBRE_TABLA, null, contentValues);
        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    public ClienteModel selectOne(String documento){
        clienteModel = new ClienteModel();
        String where = "documento=?";

        Cursor cursor = database.query(NOMBRE_TABLA, null, where, new String[]{documento}, null, null, null);
        if(cursor.getCount() < 1){
            return clienteModel;
        }
        cursor.moveToFirst();
        clienteModel.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
        clienteModel.set_documento(cursor.getString(cursor.getColumnIndex("documento")));
        clienteModel.set_nombre(cursor.getString(cursor.getColumnIndex("nombre")));

        return clienteModel;
    }

    public ArrayList<ClienteModel> selectAll(){
        clienteModels = new ArrayList<>();

        Cursor cursor = database.query(NOMBRE_TABLA, null, null, null, null, null, null);
        if(cursor.getCount() < 1){
            return clienteModels;
        }
        cursor.moveToFirst();
        do {
            clienteModel = new ClienteModel();
            clienteModel.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            clienteModel.set_documento(cursor.getString(cursor.getColumnIndex("documento")));
            clienteModel.set_nombre(cursor.getString(cursor.getColumnIndex("nombre")));

            clienteModels.add(clienteModel);
        }while (cursor.moveToNext());

        return clienteModels;
    }

    @Override
    public int getCount() {
        return clienteModels.size();
    }

    @Override
    public Object getItem(int i) {
        return clienteModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem = view;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewItem = layoutInflater.inflate(R.layout.item_cliente_lv, viewGroup, false);
        }
        TextView tv_cliente_lv_documento = viewItem.findViewById(R.id.tv_cliente_lv_documento);
        TextView tv_cliente_lv_nombre = viewItem.findViewById(R.id.tv_cliente_lv_nombre);

        clienteModel = (ClienteModel) getItem(i);
        tv_cliente_lv_documento.setText("Documento: " + clienteModel.get_documento());
        tv_cliente_lv_nombre.setText("Cliente: " + clienteModel.get_nombre());
        return viewItem;
    }
}
