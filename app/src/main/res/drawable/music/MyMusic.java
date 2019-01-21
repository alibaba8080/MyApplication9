package com.music.music;

import com.example.mymedia.R;
import com.example.util.Timeutil;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class MyMusic extends Activity {
    private SeekBar seebar;

    private TextView textView;





    private MyMusicMediaPlay mp;
    Handler handler=new Handler(){

        public void handleMessage(Message msg) {
            int what=msg.what;
            if (what==1){
                long ll=1000;
                 Timeutil timeutil=new Timeutil();
                seebar.setProgress(mp.getCurrentPosition());
           textView.setText(timeutil.timeFormate(mp.getCurrentPosition()));
                Log.e("TAG",""+mp.getCurrentPosition());
                sendEmptyMessageDelayed(1,1);
            }


        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplaylayout);
        textView = (TextView) findViewById(R.id.textView);
        seebar = (SeekBar) findViewById(R.id.seebar);
        seebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress=seekBar.getProgress();
                mp.seekTo(progress);
            }
        });
        mp=new MyMusicMediaPlay(this);

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int duration=mp.getDuration();
                seebar.setMax(duration);
                handler.sendEmptyMessage(1);
            }
        });


    }
    public void start(View v){
        mp.start();
    }
    public void pause(View v){
        mp.pause();
        Toast.makeText(this,"pause", Toast.LENGTH_SHORT).show();
    }
    public void stop(View v){
        Toast.makeText(this,"stop", Toast.LENGTH_SHORT).show();
        mp.stop();
    }
}
