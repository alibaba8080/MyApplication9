package music;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import util.DataUtil;

public class MyMusicMediaPlay extends MediaPlayer {
    private  int maxpostion= DataUtil.getList().size()-1;
    private  boolean isPlaying=true;
    int postion;
    public static int audioSessionId=0;
    public MyMusicMediaPlay(){
        Log.i("TAG","-MyMusicMediaPlay--onCreate-------");
    }
    public void startSelf(int postion){
        this.reset();
        this.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                Map<String,String> map=DataUtil.getMusicMap(postion);
                String path=map.get("data");
                this.setDataSource(path);
                this.prepare();
                this.setOnPreparedListener(new OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        audioSessionId = mp.getAudioSessionId();
                        mp.start();
                    }
                });



            }  catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}
	public void nextSelf(int postion){
        isPlaying=true;
        this.stop();
        postion++;
        if(postion>maxpostion){
            postion=0;
        }
        this.postion=postion;
        Log.e("Myservice","playNext()-----"+postion);
        this.startSelf(postion);
    }
    public void lastSelf(int postion){
        isPlaying=true;
        this.stop();
        postion--;
        if(postion<0){
            postion=maxpostion;
        }
        this.postion=postion;
        Log.e("Myservice","playLast()-----"+postion);
        this.startSelf(postion);
    }
    public void pasueSelf(){
        if(isPlaying){
            this.pause();
            isPlaying=!isPlaying;
        }else {
            this.start();
            isPlaying=!isPlaying;
        }
        Log.e("TAG",""+"MyService      pause");
    }
    public int getPostion(){
        return postion;
    }

}
