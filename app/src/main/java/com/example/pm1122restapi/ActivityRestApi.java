package com.example.pm1122restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/*METODOS RETROFIT*/
import com.example.pm1122restapi.Interface.Interfaces;
import com.example.pm1122restapi.Models.Usuarios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ActivityRestApi extends AppCompatActivity {

    ListView list;
    ArrayList<String> titulos = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        obtenerUsuario();
    }

    private void obtenerUsuario() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interfaces interfaceusers = retrofit.create(Interfaces.class);
        //objeto de Request - Peticion
        Call<List<Usuarios>> llamada = interfaceusers.getUsuarios();
        //Objeto de Response - Respuesta

        llamada.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                for (Usuarios usuarios : response.body()){
                    Log.i("On Response",usuarios.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {

            }
        });

    }


}