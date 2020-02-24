package com.jay.word_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;



/*import org.litepal.LitePal;*/

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }


        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent1 = new Intent(welcomeActivity.this, MainActivity.class);
                startActivity(intent1);
                welcomeActivity.this.finish();
            }
        };
        timer.schedule(timerTask, 1000 * 1);
        /*LitePal.getDatabase();*/

        /*handler.sendEmptyMessageDelayed(0,1000);*/
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            goSignInActivity();
            super.handleMessage(msg);
        }
    };

    public void goSignInActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
