package music;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.audiofx.Equalizer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import util.DataUtil;
import util.ScrollTextView;
import util.Timeutil;


/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class MyMusic extends Activity{
    private SeekBar seebar;
    private TextView textView;
    MyBroadcastReceiver myBro;
    private int postion;
    private ScrollTextView verticalScrollTV;
    private LinearLayout mLayout;
    private Visualizer mVisualizer;
    private Equalizer mEqualizer;
    private static final float VISUALIZER_HEIGHT_DIP = 200;//频谱View高度
    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int progress = intent.getIntExtra("progress",0);
            int max= intent.getIntExtra("max",0);
            seebar.setMax(max);
            seebar.setProgress(progress);
            textView.setText(Timeutil.timeFormate(progress));
            if(postion!=intent.getIntExtra("postion",0)){
                postion=intent.getIntExtra("postion",0);
                verticalScrollTV.setItems(DataUtil.arraylist(postion));
                verticalScrollTV.startScroll();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplaylayout);
        mLayout = (LinearLayout) findViewById(R.id.kkkk);
        textView = (TextView) findViewById(R.id.textView);
        verticalScrollTV = (ScrollTextView) findViewById(R.id.avst);
        playmusic();
        seebarInit();
        myBro = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("musicBrocast");
        registerReceiver(myBro, filter);
        setupVisualizerFxAndUi();
        setupEqualizeFxAndUi();
    }
    private void setupEqualizeFxAndUi() {

        mEqualizer = new Equalizer(0, MyMusicMediaPlay.audioSessionId);
        mEqualizer.setEnabled(true);// 启用均衡器
        // 通过均衡器得到其支持的频谱引擎
        short bands = mEqualizer.getNumberOfBands();
        // getBandLevelRange 是一个数组，返回一组频谱等级数组，
        // 第一个下标为最低的限度范围
        // 第二个下标为最大的上限,依次取出
        final short minEqualizer = mEqualizer.getBandLevelRange()[0];
        final short maxEqualizer = mEqualizer.getBandLevelRange()[1];
        for (short i = 0; i < bands; i++) {
            final short band = i;
            TextView freqTextView = new TextView(this);
            freqTextView.setTextColor(Color.WHITE);
            freqTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            freqTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            // 取出中心频率
            freqTextView.setText((mEqualizer.getCenterFreq(band) / 1000) + "HZ");
            mLayout.addView(freqTextView);
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            TextView minDbTextView = new TextView(this);
            minDbTextView.setTextColor(Color.WHITE);
            minDbTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            minDbTextView.setText((minEqualizer / 100) + " dB");
            TextView maxDbTextView = new TextView(this);
            maxDbTextView.setTextColor(Color.WHITE);
            maxDbTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            maxDbTextView.setText((maxEqualizer / 100) + " dB");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 40);
            layoutParams.weight = 1;
            SeekBar seekbar = new SeekBar(this);
            seekbar.setLayoutParams(layoutParams);
            seekbar.setPadding(20, 0, 20, 0);
            seekbar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar));
//            seekbar.setThumb(getResources().getDrawable(R.drawable.seekbar));
            seekbar.setThumbOffset(30);
            seekbar.setMax(maxEqualizer - minEqualizer);

//            seekbar.setProgress(mEqualizer.getBandLevel(band));

            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mEqualizer.setBandLevel(band, (short) (progress + minEqualizer));
                    DataUtil.saveBandLeve(MyMusic.this,band,progress);
                }

            });
            seekbar.setProgress(DataUtil.getBandLeve(this,band));
            row.addView(minDbTextView);
            row.addView(seekbar);
            row.addView(maxDbTextView);
            mLayout.addView(row);
        }
        TextView eqTextView = new TextView(this);
        eqTextView.setTextColor(Color.WHITE);
        eqTextView.setText("均衡器");
        eqTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        eqTextView.setTextSize(20);
        mLayout.addView(eqTextView);
    }
    private void setupVisualizerFxAndUi() {
        //实例化Visualizer，参数SessionId可以通过MediaPlayer的对象获得
         //频谱器
        mVisualizer = new Visualizer(MyMusicMediaPlay.audioSessionId);
        VisualizerView mBaseVisualizerView=  new VisualizerView(this);
        //false 则不显示
        mBaseVisualizerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,//宽度
                (int) (VISUALIZER_HEIGHT_DIP * getResources().getDisplayMetrics().density)//高度
        ));
        //将频谱View添加到布局
        mLayout.addView(mBaseVisualizerView);
        //采样 - 参数内必须是2的位数 - 如64,128,256,512,1024
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        //设置允许波形表示，并且捕获它
        mBaseVisualizerView.setVisualizer(mVisualizer);
        mVisualizer.setEnabled(true);
    }
        private void playmusic(){
            int postion=getIntent().getIntExtra("postion",0);
            Intent intent=new Intent(this,MyService.class);
            intent.putExtra("postion",postion);
            intent.putExtra("Action","start");
            startService(intent);
        }
    public void last(View v){
        Intent intent=new Intent(this,MyService.class);
        intent.putExtra("Action","last");
        startService(intent);
        Toast.makeText(this,"上一曲 ",Toast.LENGTH_SHORT).show();
    }
    public void playpause(View v){
        Intent intent=new Intent(this,MyService.class);
        intent.putExtra("Action","playpause");
        startService(intent);
    }
    public void next(View v){
        Intent intent=new Intent(this,MyService.class);
        intent.putExtra("Action","next");
        startService(intent);
    }
    private void seebarInit() {

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
                Intent intent=new Intent(MyMusic.this,MyService.class);
                intent.putExtra("progress",progress);
                intent.putExtra("Action","progress");
                startService(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBro);
//       mEqualizer.release();
        mVisualizer.release();
    }
}
