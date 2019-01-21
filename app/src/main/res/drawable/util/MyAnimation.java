package com.example.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

public class MyAnimation extends Animation{
	private Activity context;

	public MyAnimation(Context context){
		this.context=(Activity) context;
		
	}
	public void startAnimation(String imageName,int start,int end,int duration)
	{		
		
		AnimationDrawable animationDrawable=new AnimationDrawable();
		synchronized (animationDrawable) {
			context.getWindow().setBackgroundDrawable(animationDrawable);
			String packaName=context.getApplicationContext().getPackageName();
			for(int i=start;i<=end;i++){
				int id=context.getResources().getIdentifier(imageName+i, "drawable", packaName);
				Drawable frame=context.getResources().getDrawable(id);
				animationDrawable.addFrame(frame, duration);

			}
			animationDrawable.setOneShot(false);
		}

		animationDrawable.start();
	}
	public void alphaAnimation(View view,int duration){

		AlphaAnimation animation=new AlphaAnimation(0.5f,1f);
		synchronized (animation) {
			animation.setDuration(duration);
			animation.setRepeatCount(-1);
			animation.setRepeatMode(Animation.REVERSE);
			view.setAnimation(animation);
		}

        animation.start();
	}
	public void tranlation(View view,int x,int tox,int y,int toy,int duration){

		TranslateAnimation translateAnimation =
				new TranslateAnimation(x, tox, y, toy);

		synchronized (translateAnimation) {
			translateAnimation.setDuration(duration);
			translateAnimation.setRepeatCount(1);
			translateAnimation.setRepeatMode(Animation.REVERSE);
			view.setAnimation(translateAnimation);

		}
	}
	public void rotateAnimation(View view){
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
              Animation.RELATIVE_TO_SELF,0.5f,
              Animation.RELATIVE_TO_SELF,0.5f);
       rotateAnimation.setDuration(1000);
       rotateAnimation.setRepeatCount(-1);
       view.setAnimation(rotateAnimation);
	}
	
	
}
