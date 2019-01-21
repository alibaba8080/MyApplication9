package com.music.music;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.mymedia.R;

public class MusicActivity extends Activity {

    private Uri uri,uri1;
	private String[] projection={MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.DATA,
			MediaStore.Audio.Media.SIZE};
	private ListView  listView;
	private ArrayList<Map<String, Object>>list;
	private SimpleAdapter adapter;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_activity);
        listView=(ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent=new Intent(MusicActivity.this,MyMusic.class);
				intent.setData(Uri.parse(String.valueOf(list.get(arg2).get("data"))));
				startActivity(intent);
				// TODO Auto-generated method stub
				Toast.makeText(MusicActivity.this,""+list.get(arg2).get("data"), Toast.LENGTH_SHORT).show();
			}
		});
       
    }
    public void hello_world(View v){
        uri=MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        uri1=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    	list=new ArrayList<Map<String,Object>>();

    	Cursor cursor1=getContentResolver().query(uri, projection, null, null, null);
        Cursor cursor2=getContentResolver().query(uri, projection, null, null, null);
//    	while(cursor1.moveToNext()){
//            Map<String, Object> map1=new HashMap<String, Object>();
//    		String name=cursor1.getString(0);
//    		String data=cursor1.getString(1);
//    		long size=cursor1.getLong(2);
//    		if(size>5000){
//    			map1.put("name", name);
//        		map1.put("data", data);
//        		list.add(map1);
//    		}
//    	}

        while(cursor2.moveToNext()){
            Map<String, Object> map2=new HashMap<String, Object>();
            String name=cursor2.getString(0);
            String data=cursor2.getString(1);
            long size=cursor2.getLong(2);
            if(size>5000){
                map2.put("name", name);
                map2.put("data", data);
                list.add(map2);
            }
        }

    	 adapter=new SimpleAdapter(this, list, R.layout.listitem, new String[]{"name","data"},new int[]{R.id.name,R.id.data});
    	listView.setAdapter(adapter);
		cursor1.close();
        cursor2.close();
    }
    
}
