package com.example.mymedia;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Administrator on 2017/6/3 0003.
 */

public class Activity_0_0 extends Activity{

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_0_0);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                statVideoList();
            }
        },1500 );

    }
    private boolean isStartVideoList=false;
    private void statVideoList() {
        if(!isStartVideoList) {
            isStartVideoList=true;
            Intent intent=new Intent(this,MainActivity_1_1.class);
            startActivity(intent);
            finish();
        }
    }

}
