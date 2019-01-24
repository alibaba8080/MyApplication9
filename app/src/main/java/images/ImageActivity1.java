package images;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import util.EasyTransition;
import util.EasyTransitionOptions;

public class ImageActivity1 extends AppCompatActivity implements ImagesAdapter.OnItemClickListener {
    private long imagesize = 1024 * 10;
    private RecyclerView recyclerView;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private ImagesAdapter myAdapter;
    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() {

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                myAdapter=new ImagesAdapter(ImageActivity1.this,list);
                recyclerView.setAdapter(myAdapter);
                myAdapter.setOnItemClickListener(ImageActivity1.this);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity1);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//

//            }
//        });
        downLoad();
    }


    public void downLoad() {
        new Thread() {
            public void run() {
                Uri uri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
                Uri uri1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                String[] key = {MediaStore.Images.Media.TITLE, MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE};
                Cursor cursor = getContentResolver().query(uri, key, null, null, null);
                Cursor cursor1 = getContentResolver().query(uri1, key, null, null, null);


                while (cursor.moveToNext()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String TITLE = cursor.getString(0);
                    String DATA = cursor.getString(1);
                    long size = cursor.getLong(2);
                    if (size > imagesize) {
                        map.put(MediaStore.Images.Media.TITLE, TITLE);
                        map.put(MediaStore.Images.Media.DATA, DATA);
                        list.add(map);
                    }

                }

                while (cursor1.moveToNext()) {
                    Map<String, Object> map2 = new HashMap<String, Object>();
                    String TITLE = cursor1.getString(0);
                    String DATA = cursor1.getString(1);
                    long size = cursor1.getLong(2);

                    if (size > imagesize) {
                        map2.put(MediaStore.Images.Media.TITLE, TITLE);
                        map2.put(MediaStore.Images.Media.DATA, DATA);
                        list.add(map2);
                    }

                }
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = list;
                mhandler.sendMessage(msg);
                cursor.close();
                cursor1.close();
            }

        }.start();


    }


    @Override
    public void onItemClick(View view, int position) {
                Intent intent=new Intent(ImageActivity1.this,ImageActivity2.class);
                EasyTransitionOptions options=EasyTransitionOptions.makeTransitionOptions(ImageActivity1.this,((ImageView)view.findViewById(R.id.images)));
                intent.setData(Uri.parse((String) list.get(position).get(MediaStore.Images.Media.DATA)));
                EasyTransition.startActivity(intent,options);

    }
}
