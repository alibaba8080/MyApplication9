package util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class DataUtil {

    private static List<Map<String,String>> list=new ArrayList<Map<String, String>>();
    public static List<Map<String,String>> getList(){
        return list;
    }
    public static Map<String,String> getMusicMap(int index){
        return list.get(index);
    }
    public static String[] arraylist(int index){
       String data=list .get(index).get("data");
        String name=list .get(index).get("name");
        String[] arraylist={name,data};
        return arraylist;
    }
    public static int getBandLeve(Context context,int index){
        SharedPreferences sp = context.getSharedPreferences("BandLeve",MODE_PRIVATE);
        SharedPreferences read=sp;
       int BandLeve=read.getInt(""+index,0);
        return BandLeve;
    }
    public static void saveBandLeve(Context context,int index,int progress){
        SharedPreferences  sp = context.getSharedPreferences("BandLeve",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt(""+index,progress);
        editor.commit();
    }
}
