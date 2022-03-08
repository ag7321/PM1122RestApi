package com.example.pm1122restapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

            }
        });

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
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}