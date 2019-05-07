package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dao.DBHelper;
import object.Person;


public class MainActivity_1_2 extends Activity{
    private EditText editText1;
    private EditText editText2;
    private String name,password;
    private SQLiteDatabase db;
    private String TABLE_NAME="users";
    private String USER_NAME="name";
    private String USER_PASSWORDS="password";


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1_2);
        DBHelper helper=new DBHelper(this);
        db=helper.getWritableDatabase();
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

    }
    public void onClick(View v){
        name=editText1.getText().toString();
        password=editText2.getText().toString();

        if(name.length()>2){
            if(password.length()>5){
                Person person=new Person();
                person.setName(name);
                person.setPasswords(password);
                ContentValues values=new ContentValues();
                values.put(USER_NAME, person.getName());
                values.put(USER_PASSWORDS, person.getPasswords());
                db.insert(TABLE_NAME, null, values);
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity_1_2.this);
                builder.setMessage("账号"+person.getName()+"\n"+"密码"+person.getPasswords());
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }else{
                Toast.makeText(MainActivity_1_2.this,"密码不能小于6个字",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(MainActivity_1_2.this,"账号不能小于10个字",Toast.LENGTH_LONG).show();
        }
    }
    public void onAdmini(View v){
        Intent intent=new Intent(MainActivity_1_2.this,MainActivity_1_3.class);
        startActivity(intent);
    }
}
