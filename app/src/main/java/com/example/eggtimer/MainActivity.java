package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView countdownTextView;
    SeekBar timerSeekBar;
    boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        countdownTextView.setText("0 : 30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO");
        counterIsActive = false;
    }
    public void buttonClicked(View view){

        if (counterIsActive){
            resetTimer();
        }else{

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP");


           // Log.i("Button pressed","Nice!");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress()*1000 + 100 , 1000){

            @Override
            public void onTick(long l) {
                updateTimer((int) l/1000);
            }

            @Override
            public void onFinish() {
                //Log.i("Finished", "Timer all done");
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mplayer.start();
                resetTimer();
            }
        }.start();
    }
    }
    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);
        if(seconds <= 9){
            secondString = "0" + secondString;
        }
        countdownTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        countdownTextView = findViewById(R.id.countdownTextView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
