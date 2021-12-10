package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PlaySong extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            mediaPlayer.stop();
        }catch (NullPointerException ignored){

        }
        try{
            mediaPlayer.release();

        }catch (NullPointerException ignored){

        }
        updateSeek.interrupt();
    }

    TextView textView;
    ImageView play, previous, next;
    ArrayList<File> songs;
    MediaPlayer mediaPlayer;
    String textContent;
    int position;
    SeekBar seekBar;
    Thread updateSeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        textView = findViewById(R.id.textView2);
        play = findViewById(R.id.play);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.seekBar);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        songs = (ArrayList) bundle.getParcelableArrayList("songlist");
        textContent = intent.getStringExtra("currentSong");
        textView.setText(textContent);
        textView.setSelected(true);
        position = intent.getIntExtra("position",0);
        Uri uri = null;
        try{
            uri = Uri.parse(songs.get(position).toString());

        }catch (NullPointerException ignored){

        }
        try{
            mediaPlayer = MediaPlayer.create(this,uri);

        }catch (NullPointerException ignored){

        }
        try{
            mediaPlayer.start();

        }catch (NullPointerException ignored){

        }
        try{
            seekBar.setMax(mediaPlayer.getDuration());
        }catch (NullPointerException ignored){

        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                try{
                    mediaPlayer.seekTo(seekBar.getProgress());
                }catch (NullPointerException ignored){

                }


            }
        });
        updateSeek = new Thread(){
            @Override
            public void run() {
               int currentPosition =0;
               try{
                   while(currentPosition<mediaPlayer.getDuration()){
                       currentPosition = mediaPlayer.getCurrentPosition();
                       seekBar.setProgress(currentPosition);
                       sleep(800);
                   }

               }catch(Exception e){
                   e.printStackTrace();
               }
            }
        };
        updateSeek.start();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try{
                   if(mediaPlayer.isPlaying()){
                       play.setImageResource(R.drawable.play);
                       mediaPlayer.pause();
                   }
                   else {
                       play.setImageResource(R.drawable.pause);
                       mediaPlayer.start();
                   }
               }catch (NullPointerException ignored){

               }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mediaPlayer.stop();

                }catch (NullPointerException ignored){

                }
                try{
                    mediaPlayer.release();
                }catch (NullPointerException ignored){

                }
                try{
                    if(position!=0){
                        position = position-1;
                    }
                    else{
                        position =songs.size()-1;
                    }
                }catch (NullPointerException ignored){

                }


                Uri uri = null;
                try{
                    uri = Uri.parse(songs.get(position).toString());

                }catch (NullPointerException ignored){

                }
                try{
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);

                }catch (NullPointerException ignored){

                }
                try{
                    mediaPlayer.start();

                }catch (NullPointerException ignored){

                }
                play.setImageResource(R.drawable.pause);
                try{
                    seekBar.setMax(mediaPlayer.getDuration());
                }catch (NullPointerException ignored){

                }
                try{
                    textContent = songs.get(position).getName().toString();
                }catch (NullPointerException ignored){

                }
                textView.setText(textContent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try{
                   mediaPlayer.stop();

               }catch (NullPointerException ignored){

               }
               try{
                   mediaPlayer.release();
               }catch (NullPointerException ignored){

               }
               try{
                   if(position!=songs.size()-1){
                       position = position+1;
                   }
                   else{
                       position =0;
                   }
               }catch (NullPointerException ignored){

               }
                Uri uri = null;
                try{
                    uri = Uri.parse(songs.get(position).toString());

                }catch (NullPointerException ignored){

                }
                try{
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);

                }catch (NullPointerException ignored){

                }
                try{
                    mediaPlayer.start();

                }catch (NullPointerException ignored){

                }
                play.setImageResource(R.drawable.pause);
                try{
                    seekBar.setMax(mediaPlayer.getDuration());
                }catch (NullPointerException ignored){

                }
               try{
                   textContent = songs.get(position).getName().toString();
               }catch (NullPointerException ignored){

               }
                textView.setText(textContent);
            }
        });

    }
}