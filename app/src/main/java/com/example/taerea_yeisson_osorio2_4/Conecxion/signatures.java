package com.example.taerea_yeisson_osorio2_4.Conecxion;

public class signatures {
    public static final String NameDatabase = "PM01DB";

    public static final String tablacontactos = "firmas";

    public static final String id = "id";
    public static final String Descripcion = "Descripcion";
    public static final String firma = "firma";


    public static final String createTableContact = "CREATE TABLE " + tablacontactos +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Descripcion TEXT, firma BLOB)";

    public  static  final String Selectfirmas ="select id, Descripcion, firma from "+tablacontactos;

    public static final String dropTableContact = "DROP TABLE IF EXIST" + tablacontactos;
}
