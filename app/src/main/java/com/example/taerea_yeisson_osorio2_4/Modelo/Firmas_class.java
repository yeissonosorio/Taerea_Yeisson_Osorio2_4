package com.example.taerea_yeisson_osorio2_4.Modelo;

public class Firmas_class {

    private int id;
    private String Descripcion;
    private byte[] image;

    public Firmas_class(int id, String descripcion, byte[] image) {
        this.id = id;
        Descripcion = descripcion;
        this.image = image;
    }

    public Firmas_class() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }
}
