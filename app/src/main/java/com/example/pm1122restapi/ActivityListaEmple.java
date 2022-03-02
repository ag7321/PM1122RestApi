package com.example.pm1122restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityListaEmple extends AppCompatActivity {
    ListView listempleado;
    List<Empleado> empleadoList;
    ArrayList<String> arrayEmple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_emple);

        listempleado = findViewById(R.id.listempleado);
        empleadoList = new ArrayList<>();
        arrayEmple = new ArrayList<String>();

        ConsumirListaEmpleado();
    }

    private void ConsumirListaEmpleado(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ResApiMetodos.EndPointGetEmple, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray empleArray = jsonObject.getJSONArray("empleado");

                        for (int i = 0; i < empleArray.length(); i++) {
                            JSONObject RowEmple = empleArray.getJSONObject(i);
                            Empleado empleado = new Empleado(RowEmple.getString("id"),
                                                            RowEmple.getString("nombre"),
                                                            RowEmple.getString("apellidos"),
                                                            RowEmple.getString("edad"));

                            empleadoList.add(empleado);
                            arrayEmple.add(empleado.getNombre()+' '+empleado.getApellidos());
                        }

                        ArrayAdapter adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayEmple);
                        listempleado.setAdapter(adp);
                    }catch (JSONException e){

                    }

                }
            },new Response.ErrorListener(){
            @Override
                public void onErrorResponse(VolleyError error) {

                }
        });
        queue.add(stringRequest);
    }
}