package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBarVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.teste);

        inicializarSeakbar();



    }

    private void inicializarSeakbar(){
        seekBarVolume = findViewById(R.id.loudSeekBar);

// configurar audio manager
       audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

       //recuperar o volume máximo

        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual  = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // configurar o volume maximo
        seekBarVolume.setMax(volumeMaximo);
        seekBarVolume.setProgress(volumeAtual);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void executarSom(View view){

        if (mediaPlayer != null){
            mediaPlayer.start();
        }

    }

    public void pausarSom(View view){
        if (mediaPlayer.isPlaying()){
           mediaPlayer.pause();
        }
    }

    public  void pararSom(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bach);
        }


    }
}