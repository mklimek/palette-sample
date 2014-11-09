package brightinventions.palletesample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ImageActivity extends Activity {

    private Target target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ImageView image = (ImageView) findViewById(R.id.image);
        target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        image.setImageBitmap(bitmap);
                        int gray = getResources().getColor(R.color.black);
                        toolbar.setBackgroundColor(palette.getLightVibrantColor(gray));
                        toolbar.setTitleTextColor(palette.getDarkMutedColor(gray));
                        toolbar.setTitle(getString(R.string.app_name));
                    }
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(this)
                .load(getIntent().getStringExtra("url"))
                .into(target);
    }
}
