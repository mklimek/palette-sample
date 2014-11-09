package brightinventions.palletesample;


import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {

    private Context context;

    public String[] imageUrls = {
            "http://hdwpin.com/wp-content/uploads/2014/10/landscape-and-greenery.jpg",
            "http://cdn.wonderfulengineering.com/wp-content/uploads/2014/07/HD-landscape-Photographs.png",
            "http://www.ifullscreen.com/wallpapers/earth_landscape_wallpaper_166-2560x1440.jpg",
            "http://digital-photography-school.com/wp-content/uploads/flickr/205125227_3f160763a0_o.jpg",
            "http://cdn.wonderfulengineering.com/wp-content/uploads/2014/07/HD-landscape-Photographs-2.jpg",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTJyXieSoJuuvn3Jo88HD1TbIfIS0C1KvrtqZD76gtJlrUU0OX4",
            "http://www.homelandguild.org/wp-content/uploads/2014/06/front-yard-landscape-landscape-lighting-landscaping-design-landscaping-ideas-rock-landscaping-garden-landscape-landscape-architecture-landscape-group-landscape-supplies-landscape-structures-landsc.jpg",
            "http://www.hdwallpapersinn.com/wp-content/uploads/2014/07/White-Nature-Landscape-Wallpaper-HD.jpg"

    };

    public ImageAdapter(Context c) {
        context = c;
    }

    public int getCount() {
        return imageUrls.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.findViewsIn(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.updateWith(imageUrls[position]);
        return convertView;
    }

    class ViewHolder{
        private ImageView imageView;

        public void findViewsIn(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.image);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = getItemWidth();
            layoutParams.width = getItemWidth();
            imageView.setLayoutParams(layoutParams);
        }

        public void updateWith(String imageUrl) {
            Picasso.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.rectangle)
                    .resize(getItemWidth(), getItemWidth())
                    .centerCrop()
                    .into(imageView);
        }

        private int getItemWidth(){
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            return point.x / context.getResources().getInteger(R.integer.numColumns); // screen width / numColumns;
        }
    }


}