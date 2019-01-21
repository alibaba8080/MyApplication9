package util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2017/6/11 0011.
 */

public class SampleSzie {

    private int scale;

    public  Bitmap SampleSzie(String bitmapPath){

        BitmapFactory.Options opts=new BitmapFactory.Options();
        opts.inJustDecodeBounds=true;
        Bitmap bmp = BitmapFactory.decodeFile(bitmapPath,opts);
        int picWidth = opts.outWidth;// 寰楀埌鍥剧墖瀹藉害
        int picHeight = opts.outHeight;// 寰楀埌鍥剧墖楂樺害
//        Log.e("鍘熷浘鐗囬珮搴︼細",picHeight+"");
//        Log.e("鍘熷浘鐗囧搴︼細", picWidth + "");

        int dx = picWidth/200;//璁剧疆鍥炬爣鍒嗚鲸鐜�
        int dy = picHeight/200;
        if(dx>=dy&&dy>=1){
//            Log.e("鎸夌収姘村钩鏂瑰悜缂╂斁:" ,dx+"");
            scale = dx;
        }
        if(dy>dx&&dx>=1){
//            Log.e("鎸夌収绔栫洿鏂瑰悜缂╂斁:", dy + "");
            scale = dy;
        }
        opts.inSampleSize=scale;
        opts.inJustDecodeBounds=false;
        try {
            bmp = BitmapFactory.decodeFile(bitmapPath, opts);
        }catch (OutOfMemoryError e){
            bmp=null;
        }
        return bmp;
    }
}
