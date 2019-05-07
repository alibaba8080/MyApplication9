package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import object.Person;


public class DBHelper extends SQLiteOpenHelper {
private final static String DB_NAME="users.db";
private final static int DB_VERSION=1;
private  String TABLE_NAME="users";
private String USER_NAME="name";
private String USER_PASSWORDS="password";
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		
		String ExecSQL="CREATE TABLE "+TABLE_NAME+"(_id integer primary key not null,"+USER_NAME+" CHAR(20),"+USER_PASSWORDS+" CHAR(20))";
		arg0.execSQL(ExecSQL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		if(arg2>arg1){
		String sql="DROP TABLE IF EXISTS "+TABLE_NAME;		
			arg0.execSQL(sql);
			onCreate(arg0);
		}
	}
	public void insert(SQLiteDatabase arg0){
		for (int i=1;i<=60;i++) {
			Person person = new Person();
			person.setName("140880510" + i);
			person.setPasswords("123456");
			ContentValues values = new ContentValues();
			values.put(USER_NAME, person.getName());
			values.put(USER_PASSWORDS, person.getPasswords());
			arg0.insert(TABLE_NAME, null, values);
		}
	}

}
