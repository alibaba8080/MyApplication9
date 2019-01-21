package mysurfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myapplication.R;


/**
 * Created by Administrator on 2017/5/30 0030.
 */

public class MyToggleButton extends View  {
    private Bitmap switch_h,switch_d;
    int screnWidth,screnHeight;
    private Paint paint;
    private boolean isOpen;

    public MyToggleButton(Context context) {
        super(context);

    }
    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        switch_h=BitmapFactory.decodeResource(getResources(),  R.drawable.switch_h);
        switch_d=BitmapFactory.decodeResource(getResources(),R.drawable.switch_d);
        screnWidth=switch_h.getWidth();
        screnHeight=switch_h.getHeight();
    }

    public boolean getisOpen() {
        return isOpen;

    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(screnWidth,screnHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if(isOpen) {
            canvas.drawBitmap(switch_h, 0, 0, paint);
        }else{
            canvas.drawBitmap(switch_d, 0, 0, paint);
        }
    }





}
