package fmy.qf.com.mancheng.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.animation.Constant;
import fmy.qf.com.mancheng.animation.SwitchAnimationUtil;

public class SaplashActivity extends AppCompatActivity {

    private ImageView iv;
asd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(), Constant.mType);
        setContentView(R.layout.activity_saplash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        iv = ((ImageView) findViewById(R.id.logo));
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_saplash);
        iv.startAnimation(animation);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SaplashActivity.this.startActivity(new Intent(SaplashActivity.this,HomeActivity.class));
                finish();

            }
        },3000);
    }
}
