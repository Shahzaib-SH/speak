package com.example.sign_recognition;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.RunnableFuture;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    Animation topAnim,botAnim,sideAnim;
    ImageView bg_image,s_image;
    TextView text;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        transparent_Nav_bar();
        setContentView(R.layout.activity_splash__screen);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bot_anim);
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);

        text = findViewById(R.id.splash_text);
        bg_image = findViewById(R.id.bg_image);
        s_image = findViewById(R.id.sign_image);





        s_image.setAnimation(botAnim);
        text.setAnimation(sideAnim);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

               sharedPreferences = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

               boolean is_first_time = sharedPreferences.getBoolean("first_time",true);

               if(is_first_time)
               {
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putBoolean("first_time",false);
                   editor.commit();


                   Intent intent = new Intent(Splash_Screen.this,OnBoarding.class);
                   startActivity(intent);
                   finish();

               }
               else {
                   Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
                   startActivity(intent);
                   finish();
               }


           }
       },SPLASH_SCREEN);


    }

    private  void transparent_Nav_bar(){

        if (Build.VERSION.SDK_INT>=19 && Build.VERSION.SDK_INT<21)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        }
        else if (Build.VERSION.SDK_INT>=21)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        }

    }
}