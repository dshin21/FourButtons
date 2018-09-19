package com.example.danie.fourbuttons;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Button changeColorBtn = findViewById( R.id.changeColorBtn );
        Button speakBtn = findViewById( R.id.speakBtn );
        Button apiVersionBtn = findViewById( R.id.apiVersionBtn );
        Button serialNumberBtn = findViewById( R.id.serialNumberBtn );

        changeColorBtn.setOnClickListener( ( View v ) -> changeColor() );
        speakBtn.setOnClickListener( ( View v ) -> speak() );
        apiVersionBtn.setOnClickListener( ( View v ) -> showAPIVersion() );
        serialNumberBtn.setOnClickListener( ( View v ) -> sendSerialNumber() );
    }

    private void changeColor() {
        RelativeLayout container = findViewById( R.id.container );
        Random rand = new Random();
        container.setBackgroundColor( Color.rgb(
                rand.nextInt( 255 ),
                rand.nextInt( 255 ),
                rand.nextInt( 255 ) ) );
    }

    private void speak() {
        Intent intent = new Intent( this, SpeakActivity.class );
//        intent.putExtra( "msg", messageText );
        startActivity( intent );

    }

    private void showAPIVersion() {
        String manufacturer = android.os.Build.MANUFACTURER;
        String model = android.os.Build.MODEL;
        int version = android.os.Build.VERSION.SDK_INT;
        String versionRelease = android.os.Build.VERSION.RELEASE;

        String messageText = "manufacturer " + manufacturer
                + " \n model " + model
                + " \n version " + version
                + " \n versionRelease " + versionRelease;
        Toast toast = Toast.makeText( getApplicationContext(), messageText, Toast.LENGTH_LONG );
        toast.show();
    }

    private void sendSerialNumber() {
        String deviceId = Settings.System.getString( getContentResolver(), Settings.System.ANDROID_ID );
        Intent emailIntent = new Intent( Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "test@gmail.com", null ) );
        emailIntent.putExtra( Intent.EXTRA_SUBJECT, "Hi Medhat" );
        emailIntent.putExtra( Intent.EXTRA_TEXT, deviceId );
        startActivity( Intent.createChooser( emailIntent, "Send email..." ) );
    }
}
