package util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2017/6/11 0011.
 */

public class SampleSzie {

    private int scale;

    public static  Bitmap SampleSzie(String bitmapPath){

        BitmapFactory.Options opts=new BitmapFactory.Options();
        opts.inJustDecodeBounds=true;
        Bitmap bmp = BitmapFactory.decodeFile(bitmapPath,opts);
        int picWidth = opts.outWidth;
        int picHeight = opts.outHeight;

        int dx = picWidth/200;
        int dy = picHeight/200;
//        if(dx>=dy&&dy>=1){
//            scale = dx;
//        }
//        if(dy>dx&&dx>=1){
//            scale = dy;
//        }
        opts.inSampleSize=dy;
        opts.inJustDecodeBounds=false;
        try {
            bmp = BitmapFactory.decodeFile(bitmapPath, opts);
        }catch (OutOfMemoryError e){
            bmp=null;
        }
        return bmp;
    }
}
