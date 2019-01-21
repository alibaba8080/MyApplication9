package com.example.mysurfaceview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

public class MyMediaPlay extends MediaPlayer{

	private AssetFileDescriptor assfile;


	public MyMediaPlay(Context context, SurfaceHolder holder){
		Log.i("TAG", "MyMediaPlay");

		this.setAudioStreamType(AudioManager.STREAM_MUSIC);
		this.setDisplay(holder);
			    try {
			    	assfile=context.getResources().getAssets().openFd("mqr.mp4");
			    	this.setDataSource(assfile.getFileDescriptor(),assfile.getStartOffset(),assfile.getLength());
					this.prepare();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    loop();
	}
	public void loop(){
		this.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				Log.i("TAG", "onCompletion");
				mp.start();
			}
		});
	}

   
}
