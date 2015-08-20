package com.example.s14003.text_to_speech;

import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class Lesson5 extends AppCompatActivity
        implements View.OnClickListener, TextToSpeech.OnInitListener {
    private TextToSpeech    tts;
    private Button buttonSmile;
    private Button buttonSea ;
    private Button buttonAnt;
    private Button Next;
    public MyCountDownTimer cdt;
    public TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ant);

        buttonSmile = (Button)findViewById(R.id.ButtonSmile);
        buttonSmile.setOnClickListener(this);
        buttonSea = (Button)findViewById(R.id.ButtonSea);
        buttonSea.setOnClickListener(this);
        buttonAnt = (Button)findViewById(R.id.ButtonAnt);
        buttonAnt.setOnClickListener(this);
        Next = (Button)findViewById(R.id.Next);
        Next.setOnClickListener(this);

        time=(TextView)findViewById(R.id.time);
        //btn3=(Button)findViewById(R.id.stop);
        cdt = new MyCountDownTimer(30*1000, 100);
        cdt.start();

        tts = new TextToSpeech(this, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ant, menu);
        return true;



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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setTButton(){ //・・・【5】
        Next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cdt.cancel();
                setSButton();
            }
        });
    }


    private void setSButton(){ //・・・【6】
        Next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Next.setText("一時停止");
                String time1 = time.getText().toString();
                String[] time2 = time1.split(":", 0);

                cdt = new MyCountDownTimer((Integer.parseInt(time2[0]) * 60 + Integer.parseInt(time2[1])) * 1000, 100);
                cdt.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != tts) {
            // TextToSpeechのリソースを解放する
            tts.shutdown();
        }
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (buttonSmile == v) {
            speechSmile();
        } else if (buttonSea == v) {
            speechSea();
        } else if (buttonAnt == v) {
            speechAnt();
        } else if (Next == v) {
            Intent it = new Intent(this, Lesson6.class);
            startActivity(it);
        }

    }

    /**
     * Called to signal the completion of the TextToSpeech engine initialization.
     *
     * @param status {@link TextToSpeech#SUCCESS} or {@link TextToSpeech#ERROR}.
     */
    @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
            Locale locale = Locale.ENGLISH;
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
            } else {
                Log.d("", "Error SetLocale");
            }
        } else {
            Log.d("", "Error Init");
        }

    }
    private void speechSmile() {
        String string = ((Button)findViewById(R.id.ButtonSmile)).getText().toString();

        if (0 < string.length()) {
            if (tts.isSpeaking()) {
                // 読み上げ中なら止める
                tts.stop();
            }

            // 読み上げ開始
            tts.speak(string, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void speechSea() {
        String string = ((Button)findViewById(R.id.ButtonSea)).getText().toString();

        if (0 < string.length()) {
            if (tts.isSpeaking()) {
                // 読み上げ中なら止める
                tts.stop();
            }

            // 読み上げ開始
            tts.speak(string, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void speechAnt() {
        String string = ((Button)findViewById(R.id.ButtonAnt)).getText().toString();

        if (0 < string.length()) {
            if (tts.isSpeaking()) {
                // 読み上げ中なら止める
                tts.stop();
            }

            // 読み上げ開始
            tts.speak(string, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}
