package com.example.momento_uno.models;
import java.io.Serializable;

public class ClienteModel implements Serializable {
    private int _id;
    private String _documento;
    private String _nombre;

    public ClienteModel() {
    }

    public ClienteModel(String _documento, String _nombre) {
        this._documento = _documento;
        this._nombre = _nombre;
    }

    public ClienteModel(int _id, String _documento, String _nombre) {
        this._id = _id;
        this._documento = _documento;
        this._nombre = _nombre;
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "_id=" + _id +
                ", _documento='" + _documento + '\'' +
                ", _nombre='" + _nombre + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_documento() {
        return _documento;
    }

    public void set_documento(String _documento) {
        this._documento = _documento;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }



}
