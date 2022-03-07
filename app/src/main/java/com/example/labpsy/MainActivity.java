package com.example.labpsy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button timebutton,startbotton;
TextView resultmsg;
Chronometer chronometer;
long random,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timebutton=(Button)findViewById(R.id.timebutton);
        resultmsg=(TextView)findViewById(R.id.textView);
        startbotton=(Button)findViewById(R.id.start);
        chronometer =(Chronometer)findViewById(R.id.chronometer);
    }
    public  void startalert(View view){
        resultmsg.setText("");
        startbotton.setEnabled(false);
        random = new Random(99999).nextInt((20000-10000)+1);
        timebutton.setBackgroundColor(Color.GREEN);
        timebutton.setText("Tap when color is RED");
        //Toast.makeText(this, "timer time "+random, Toast.LENGTH_SHORT).show();
        new CountDownTimer(random,10){
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                timebutton.setEnabled(true);
                timebutton.setBackgroundColor(Color.RED);
            }
        }.start();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }
    public void helpalert(View view){
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Instructions");
        builder.setMessage("\n \n Click the start button to test. Click the green button when it turns red. the difference between the app timer and your reaction time will be displayed above." +
                " negative sign indicates your delay to react" +
                "\n \n \n"+"Developed by LabMitra Project team ");
        builder.setCancelable(false);
        builder.setPositiveButton("got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void stoptimer(View view){
        result=0;
        chronometer.stop();
        long time= SystemClock.elapsedRealtime()-chronometer.getBase();
        //Toast.makeText(this, "yr time"+time, Toast.LENGTH_SHORT).show();
        result = random-time;
        resultmsg.setText("Time difference is "+result+" Milli seconds");
        chronometer.setBase(SystemClock.elapsedRealtime());
        timebutton.setText("Click start to test");
        timebutton.setBackgroundColor(Color.DKGRAY);
        timebutton.setEnabled(false);
        startbotton.setEnabled(true);
    }
}
