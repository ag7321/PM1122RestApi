package com.example.pm1122restapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ActivityImagen extends AppCompatActivity {
    ImageView imageView;
    Button btnGaleria,btnUploadImage;
    static final int RESULT_IMAGE = 200;

    Bitmap photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        imageView = (ImageView) findViewById(R.id.imageView);
        btnGaleria = (Button) findViewById(R.id.btnGaleria);
        btnUploadImage = (Button) findViewById(R.id.btnUploadImage);

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GaleriaImagenes();

            }


        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendImage();
            }
        });

    }

    private void SendImage() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ResApiMetodos.EndPointImageUpload, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Respuesta" , response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Respuesta" , error.toString());
            }
        })
        {
            protected Map<String, String> getParams(){

                String image = GetStringImage(photo);
                Map<String, String> parametros = new HashMap<String, String>();

                parametros.put("IMAGEN", image);

                return parametros;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private String GetStringImage(Bitmap photo) {

        try {
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 70,ba);
            byte[] imagebyte = ba.toByteArray();
            String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
            return encode;
        }catch (Exception e){
            e.toString();
        }


        return  "";
    }

    private void GaleriaImagenes() {
        Intent intentGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGaleria, RESULT_IMAGE);
    }

    //INVESTIGAR
    //COMO SE PUEDE CONVERTIR UNA IMAGE A UN ARRAY CON BYTE Y LA IMAGEN A UN BASE 64

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri imageUri;

        if(resultCode == RESULT_OK && requestCode == RESULT_IMAGE){

            try {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
                photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
            }catch (Exception ex){

            }

        }
    }
}