package com.example.mymedia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mymedia.R;
import com.example.util.MyAnimation;
import com.user.object.Person;


/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class MainActiviyi_2_1 extends Activity {
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_2_1);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
       Person person=new Person();
        if (person.isBoy()){
            MyAnimation animation = new MyAnimation(this);
            animation.startAnimation("bg_",1,1,3000);
        }else {
            MyAnimation animation = new MyAnimation(this);
            animation.startAnimation("bg_",1,1,3000);
        }
    }





}
