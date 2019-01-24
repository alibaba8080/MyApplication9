package images;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;

import util.EasyTransition;


/**
 * Created by Administrator on 2017/6/11 0011.
 */

public class ImageActivity2 extends AppCompatActivity {
    private ImageView image;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity2);
        image = (ImageView) findViewById(R.id.images);
        final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        Uri path = getIntent().getData();
        Bitmap bap= BitmapFactory.decodeFile(path.toString());
        Toast.makeText(this,""+path.toString(),Toast.LENGTH_SHORT).show();
        image.setImageBitmap(bap);
        EasyTransition.enter(this, 400, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        EasyTransition.exit(this, 400, new DecelerateInterpolator());
    }
}
