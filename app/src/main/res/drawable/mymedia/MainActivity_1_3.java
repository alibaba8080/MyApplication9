package com.example.mymedia;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.dbhelper.DBHelper;
import com.example.mymedia.R;
import com.user.object.Admini;
import com.user.object.Person;

public class MainActivity_1_3 extends Activity {
    private EditText editText1;
    private EditText editText2;
    private Button button1;
    private LinearLayout linearLayout1,linearLayout2;
    private SQLiteDatabase db;
    private String TABLE_NAME="users";
    private String USER_NAME="name";
    private String USER_PASSWORDS="password";
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private EditText editText3;
    private Button button3;
    private Button button4;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_3);
        init();
    }

    private void init() {
        DBHelper helper=new  DBHelper(this);
        db=helper.getWritableDatabase();
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button1);
        listView = (ListView) findViewById(R.id.listView);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        editText3 = (EditText) findViewById(R.id.editText3);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person=new Person();
                Admini admini=new Admini();
                person.setName(editText1.getText().toString());
                person.setPasswords(editText2.getText().toString());
                if(person.getName().equals (admini.getName()) && person.getPasswords().equals(admini.getPasswords())){
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.VISIBLE);
                }else{Toast.makeText(MainActivity_1_3.this, "禁止暴力管理员破�?", Toast.LENGTH_SHORT).show();}
            }
        });
        adapter=new SimpleCursorAdapter(this, R.layout.list_item, null, new
                String[]{USER_NAME,USER_PASSWORDS,"_id"}, new int[]{R.id.name,R.id.passwords,R.id.userId},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person=new Person();
                person.setName(editText3.getText().toString());
                if(person.getName().length()!=0){
                    Cursor cursor=db.query(TABLE_NAME, null,USER_NAME+"=?",new String[]{person.getName()}, null, null, null);
                    if (!cursor.moveToNext()){
                        cursor.close();
                        Toast.makeText(MainActivity_1_3.this, "没有"+person.getName()+"记录", Toast.LENGTH_SHORT).show();
                    }
                    adapter.swapCursor(cursor);
                    adapter.notifyDataSetChanged();
                }else{

                    Cursor cursor=db.query(TABLE_NAME, null,null,null, null, null, null);
                    if (!cursor.moveToNext()){
                        cursor.close();
                        DBHelper helper=new DBHelper(MainActivity_1_3.this);
                        helper.insert(db);
                        Toast.makeText(MainActivity_1_3.this, "整个数据库没有账�?" +
                                "已添加默认账�?", Toast.LENGTH_SHORT).show();
                    }
                    adapter.swapCursor(cursor);
                    adapter.notifyDataSetChanged();
                }

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person=new Person();
                person.setName(editText3.getText().toString());
                int resule = db.delete(TABLE_NAME,USER_NAME+"=?", new String[]{person.getName()});
                if(resule>0)
                {
                    Toast.makeText(MainActivity_1_3.this, "删除成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity_1_3.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
