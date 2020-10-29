package com.example.sonidoyvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button play, stop, pause;
    TextView textView;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.buttonPlay);
        pause = findViewById(R.id.buttonPause);
        stop = findViewById(R.id.buttonStop);
        textView = findViewById(R.id.textView);
        videoView = findViewById(R.id.videoView);

        mediaPlayer = MediaPlayer.create(this, R.raw.dualipa);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    textView.setText("Ya está sonando!!");
                } else {
                    mediaPlayer.start();
                    textView.setText("La canción está sonando");
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    textView.setText("Canción STOP!!");
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    textView.setText("El reproductor ya esta parado");
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    textView.setText("PAUSADA!!");
                } else {
                    textView.setText("No puedo pausarla");
                }
            }
        });

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.magia;

        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                videoView.start();
                mediaController.show();
            }
        });


    }
}