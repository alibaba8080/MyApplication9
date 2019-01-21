package music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class MyService extends Service {
    private static  MyMusicMediaPlay mp;
    private  int postion;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1)
            {
                if(mp != null)
                { Intent intent = new Intent();
                    intent.setAction("musicBrocast");
                    intent.putExtra("progress", mp.getCurrentPosition());
                    intent.putExtra("max", mp.getDuration());
                    int putpostion=0;
                    if(postion!=putpostion){
                        putpostion=postion;
                        intent.putExtra("postion",putpostion);
                    }
                    sendBroadcast(intent);
                    handler.removeMessages(1);
                    handler.sendEmptyMessageDelayed(1,500);
                }

            }
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        mp=new MyMusicMediaPlay();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e("Myservice","Myservice    onCompletion");
                playNext();
            }
        });
        handler.sendEmptyMessage(1);
        Log.i("TAG","-MyService--onCreate-------");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("Myservice","onStartCommand ");


        String Action=intent.getStringExtra("Action");
        Log.e("Myservice","-----"+Action);
        if(Action.equals("next")){
            playNext();
        }else if (intent.getStringExtra("Action").equals("last")) {
            playLast();
        }else if(Action.equals("start")){
            int getpostion=intent.getIntExtra("postion",0);
            if(postion!=getpostion){
                postion=getpostion;
                play();
            }
        }else if(Action.equals("playpause")){
            pause();
        }else if(Action.equals("progress")){
            mp.seekTo(intent.getIntExtra("progress",0));
        }
        return super.onStartCommand(intent, flags, startId);
    }
    public void play() {
        if(mp!=null){
            mp.stop();
        }
        mp.startSelf(postion);
        Log.e("Myservice","play()-----"+postion);
    }
    public  void playNext() {
        mp.nextSelf(postion);
        postion=mp.getPostion();
    }
    public  void playLast(){
        mp.lastSelf(postion);
        postion=mp.getPostion();
    }
    public  void pause() {
        mp.pasueSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      if(mp!=null){
          handler.removeCallbacksAndMessages(this);
          mp.stop();
          mp.release();
          postion=0;
          mp = null;
      }
        Log.i("TAG","音乐---播放结束-------");
}
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
