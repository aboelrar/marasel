package www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.marasel.R;


public class ViewImageProductDetails extends PagerAdapter implements View.OnClickListener {
    Context context;
    ArrayList<String> image;

    public ViewImageProductDetails(Context context,  ArrayList<String> image) {
        this.context = context;
        this.image = image;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    int pos;

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view=LayoutInflater.from(context).inflate(R.layout.product_details_slider_item,container,false);
        ImageView images=(ImageView)view.findViewById(R.id.slider);
        Glide.with(context).load(image.get(position)).into(images);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {


    }
}