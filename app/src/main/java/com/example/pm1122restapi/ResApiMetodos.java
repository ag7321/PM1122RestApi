package com.example.pm1122restapi;

public class ResApiMetodos {
    private static final String ipaddress = "192.168.0.119"; //FQON
    private static final String StringHttp = "http://";
    //EndPoint Urls
    private static final String GetEmple = "/CRUD-PHP/listaempleados.php";
    private static final String CrarEmple = "/CRUD-PHP/crear.php";
    //Metodos CRUD Rest Api para cunsumo
    private static final String ImageUpload = "/CRUD-PHP/UploadFile.php";

    //Metodo Get
    public static final String EndPointGetEmple = StringHttp + ipaddress + GetEmple;

    //Metodo post
    public static final String EndPointCrearEmple = StringHttp + ipaddress + CrarEmple;

    //Metodo para IMG
    public static final String EndPointImageUpload = StringHttp + ipaddress + ImageUpload;
}
