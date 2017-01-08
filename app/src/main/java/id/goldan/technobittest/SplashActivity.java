package id.goldan.technobittest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        new CountDownTimer(1000,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
