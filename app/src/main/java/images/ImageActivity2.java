package images;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;



/**
 * Created by Administrator on 2017/6/11 0011.
 */

public class ImageActivity2 extends Activity {
    private ImageView image;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity2);
        image = (ImageView) findViewById(R.id.image);
        Uri path = getIntent().getData();
        Bitmap bap= BitmapFactory.decodeFile(path.toString());
        Toast.makeText(this,""+path.toString(),Toast.LENGTH_SHORT).show();
        image.setImageBitmap(bap);
    }
}
