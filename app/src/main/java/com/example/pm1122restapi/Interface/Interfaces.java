package com.example.pm1122restapi.Interface;

import com.example.pm1122restapi.Models.Usuarios;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Interfaces {
    String Ruta0 = "/posts";
    String Ruta1 = "/post/{valor}";

    @GET(Ruta0)
    Call<List<Usuarios>> getUsuarios();

    @GET(Ruta1)
    Call<Usuarios> getUsusuarios(@Path("valor") String valor);

}
