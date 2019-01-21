package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import images.ImageActivity1;
import music.MusicActivity;
import music.MyService;
import util.MyAnimation;


/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class MainActiviyi_2_1 extends Activity {
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init() {
        setContentView(R.layout.layout_2_1);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        MyAnimation animation=new MyAnimation(this);
        animation.startAnimation("back",0,3,3000);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActiviyi_2_1.this, MusicActivity.class);
                startActivity(intent);
            }
        });

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActiviyi_2_1.this, ImageActivity1.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MyService.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("是否退出");
            builder.setPositiveButton("关闭音乐",new AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("退出程序",new AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    moveTaskToBack(false);
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
