package campus.podcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Akhila on 9/10/2018.
 */
public class SplashScreen extends AppCompatActivity {
    private static int splash_time_out=4000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,Home.class);
                startActivity(intent);
                finish();
            }
        },splash_time_out);
    }
}
