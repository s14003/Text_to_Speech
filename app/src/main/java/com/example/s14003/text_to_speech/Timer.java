package com.example.s14003.text_to_speech;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Timer extends Activity {
    public MyCountDownTimer cdt;
    public TextView time;
    public Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        time=(TextView)findViewById(R.id.time);
        btn3=(Button)findViewById(R.id.stop);
        cdt = new MyCountDownTimer(30*1000, 100);
        cdt.start();
        setTButton();
        /*
        Button btn1 = (Button)findViewById(R.id.start);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cdt.cancel();
                cdt = new MyCountDownTimer(30*1000, 100);
                cdt.start();
                setTButton(); //・・・【2】
            }
        });
        */
    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        //最後の問題に使う
        @Override
        public void onFinish() {
            //stopButton(); //・・・【4】
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(millisUntilFinished/1000%60>=10){
                time.setText(Long.toString(millisUntilFinished/1000/60) + ":" + Long.toString(millisUntilFinished/1000%60));
            }else{
                time.setText(Long.toString(millisUntilFinished/1000/60) + ":0" + Long.toString(millisUntilFinished/1000%60));
            }
        }
    }

    private void setTButton(){ //・・・【5】
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cdt.cancel();
                setSButton();
            }
        });
    }

    private void setSButton(){ //・・・【6】
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn3.setText("一時停止");
                String time1 = time.getText().toString();
                String[] time2 = time1.split(":", 0);

                cdt = new MyCountDownTimer((Integer.parseInt(time2[0])*60+Integer.parseInt(time2[1]))*1000, 100);
                cdt.start();
            }
        });
    }

    /* 最後の問題に使う
    private void stopButton(){ //・・・【7】
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
    */
}
