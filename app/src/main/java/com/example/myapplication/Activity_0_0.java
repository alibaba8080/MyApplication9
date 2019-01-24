package com.example.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;

import util.MyAnimation;

/**
 * Created by Administrator on 2017/6/3 0003.
 */

public class Activity_0_0 extends Activity{
    private ImageView imageView1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_0_0);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
//        MyAnimation animation = new MyAnimation(this);
//        animation.startAnimation("bg_",1,1,3000);
//        animation.alphaAnimation(imageView1,4000);
        imageView1.animate()
                .setDuration(4000)
                .alpha(1);
//        new Handler().postDelayed(new Runnable(){
//
//            @Override
//            public void run() {
//                start();
//            }
//        },2500 );

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        start();
        return true;

    }

    private boolean isStart=false;
    public void start() {
        if(!isStart) {
            isStart=true;
            Intent intent=new Intent(this,MainActivity_1_1.class);
            startActivity(intent);
            finish();
        }
    }

}
