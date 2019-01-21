package com.example.mysurfaceview;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView  implements SurfaceHolder.Callback{
	private Context context;
	private MyMediaPlay mp;
	private SurfaceHolder holder;
	
	public MySurfaceView(Context context,SurfaceView surfaceView) {
		this.context=context;
		this.holder=surfaceView.getHolder();
		holder.addCallback(this);

	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("TAG","------surfaceCreated-------");
		mp=new MyMediaPlay(context, holder);
		mp.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("TAG","------播放结束-------");
		mp.release();
	}

}
