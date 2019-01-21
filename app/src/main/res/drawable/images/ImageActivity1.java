package com.example.images;

import android.app.Activity;
import com.example.mymedia.R;
import com.example.util.SampleSzie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageActivity1 extends Activity {


    private GridView gridView;
    private List<Map<String, Object>> list;
    private Handler mhandler= new Handler(){

        public void handleMessage(Message msg) {
            if(msg.what==1) {
                gridView.setAdapter(new MyAdapter());
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity1);
        gridView=(GridView) findViewById(R.id.gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(ImageActivity1.this,ImageActivity2.class);
                intent.setData(Uri.parse((String) list.get(position).get(MediaStore.Images.Media.DATA)));
                startActivity(intent);
            }
        });
        downLoad();
    }


    public void downLoad() {
        new Thread(){
            public void run(){
        list=new ArrayList<Map<String,Object>>();
        Uri uri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
        Uri uri1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] key = {MediaStore.Images.Media.TITLE, MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, key, null, null, null);
        Cursor cursor1 = getContentResolver().query(uri1, key, null, null, null);


            while (cursor.moveToNext()) {
                Map<String,Object> map=new HashMap<String, Object>();
                String TITLE = cursor.getString(0);
                String DATA = cursor.getString(1);
                map.put(MediaStore.Images.Media.TITLE, TITLE);
                map.put(MediaStore.Images.Media.DATA, DATA);
                list.add(map);
            }

            while (cursor1.moveToNext()) {
                Map<String,Object> map2=new HashMap<String, Object>();
                String TITLE = cursor1.getString(0);
                String DATA = cursor1.getString(1);
                map2.put(MediaStore.Images.Media.TITLE, TITLE);
                map2.put(MediaStore.Images.Media.DATA, DATA);
                list.add(map2);
            }

                Message msg=Message.obtain();
                msg.what=1;
                msg.obj=list;
                mhandler.sendMessage(msg);
            };
        }.start();


        }



    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.grid_item, null);
                holder=new ViewHolder();
                holder.iv=(ImageView) convertView.findViewById(R.id.images);
                holder.tv=(TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }

            holder.tv.setText((CharSequence) list.get(position).get(MediaStore.Images.Media.TITLE));


             SampleSzie s=new SampleSzie();
             holder.iv.setImageBitmap(s.SampleSzie((String)list.get(position).get(MediaStore.Images.Media.DATA)));

            return convertView;
        }

    }
    static class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
