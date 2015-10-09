package assessment.mycompany.com.emojis.activities;

import android.content.Intent;
import android.os.Bundle;

import assessment.mycompany.com.emojis.R;


public class SplashActivity extends BaseActivity {

    @Override
    public String getTag() {
        return SplashActivity.class.getCanonicalName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    finish();
                    Intent loginIntent = new Intent(getApplicationContext(), EmojisActivity.class);
                    startActivity(loginIntent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        backgroundThread.start();

    }




}
