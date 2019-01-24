package com.example.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import dbhelper.DBHelper;
import mysurfaceview.MySurfaceView;
import mysurfaceview.MyToggleButton;
import object.Person;



public class MainActivity_1_1 extends Activity {
    private ImageButton imageButton1;
    private EditText editText1;
    private EditText editText2;
    private SurfaceView surfaceView;
    private MyToggleButton curstom;
    private Button button1;
    private SQLiteDatabase db;
    private String TABLE_NAME="users";
    private String USER_NAME="name";
    private String USER_PASSWORDS="password";
    private Person person;
    boolean setOpen ;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_1);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        curstom = (MyToggleButton) findViewById(R.id.curstom);
        sp=getSharedPreferences("isOpen",MODE_PRIVATE);
        curstom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOpen=!setOpen;
                curstom.setOpen(setOpen);
                SharedPreferences.Editor editor=sp.edit();
                editor.putBoolean("isOpen",setOpen);
                editor.commit();
            }
        });

        new MySurfaceView(this,surfaceView);
        init1();
        savePassword();
        inserUser();

    }

    private void init1() {
    	DBHelper helper=new DBHelper(this);
    	db=helper.getWritableDatabase();
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person=new Person();
                person.setName(editText1.getText().toString());
                person.setPasswords(editText2.getText().toString());
                Cursor cursor=db.query(TABLE_NAME, null,USER_NAME+"=?",new String[]{person.getName()}, null, null, null);
                if(cursor.moveToNext()){
                    cursor.close();
                    Cursor cursor1=db.query(TABLE_NAME, null,USER_PASSWORDS+"=?",new String[]{person.getPasswords()}, null, null, null);
                    if(cursor1.moveToNext()){
                        cursor1.close();
                        if(setOpen) {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString(USER_NAME, person.getName());
                            editor.putString(USER_PASSWORDS, person.getPasswords());
                            editor.commit();
                            Log.i("TAG", "保存");
                        }
                        Intent intent=new Intent(MainActivity_1_1.this,MainActiviyi_2_1.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(MainActivity_1_1.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity_1_1.this, "密码不正确", Toast.LENGTH_SHORT).show();
                    }
                }else{Toast.makeText(MainActivity_1_1.this, "用户名不存在", Toast.LENGTH_SHORT).show();}
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_1_1.this,MainActivity_1_2.class);
                startActivity(intent);
            }
        });
    }
    public void savePassword(){
        SharedPreferences read=sp;
        setOpen=read.getBoolean("isOpen", Boolean.parseBoolean(""));
        curstom.setOpen(setOpen);
        if(setOpen) {
            editText1.setText(read.getString(USER_NAME,""));
            editText2.setText(read.getString(USER_PASSWORDS,""));
            Log.i("TAG", "curstom.setOpen(setOpen)" + setOpen);
        }
    }
    public void inserUser(){
        Cursor cursor=db.query(TABLE_NAME, null,null,null, null, null, null);
        if (!cursor.moveToNext()){
            DBHelper helper=new DBHelper(this);
            helper.insert(db);
            Toast.makeText(this, "整个数据库没有账号" +
                    "已添加默认账号", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
