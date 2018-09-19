package com.example.danie.fourbuttons;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.Locale;

public class SpeakActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private Button btnSpeak;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_speak );
        tts = new TextToSpeech( this, this );

        btnSpeak = findViewById( R.id.backBtn );

        btnSpeak.setOnClickListener( arg0 -> finish() );
    }

    @Override
    public void onInit( int status ) {
        if ( status == TextToSpeech.SUCCESS ) {
            int result = tts.setLanguage( Locale.US );
            if ( result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED ) {
                Log.e( "TTS", "This Language is not supported" );
            } else {
                btnSpeak.setEnabled( true );
                tts.speak( "hello", TextToSpeech.QUEUE_FLUSH, null );
            }
        } else {
            Log.e( "TTS", "Initilization Failed!" );
        }
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if ( tts != null ) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
