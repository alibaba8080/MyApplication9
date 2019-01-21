package com.music.music;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class MyMusicMediaPlay extends MediaPlayer {


    private final Uri uri;

    public MyMusicMediaPlay(Context context){
        this.setAudioStreamType(AudioManager.STREAM_MUSIC);


        uri=((Activity) context).getIntent().getData();
        if(uri!=null){
            try {
                this.setDataSource(context, uri);
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
        }else{
            Toast.makeText(context, "无效路径", Toast.LENGTH_LONG).show();
        }
	}
	public void loop(){
		this.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				Log.i("TAG", "onCompletion");

			}
		});
	}

   
}
