package www.gift_vouchers.marasel.MainScreen.ui.home.pattern;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Categories.ui.Categories;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.MaraselServiceList;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.utils.utils;

public class MaraselServiceAdapter extends RecyclerView.Adapter<MaraselServiceAdapter.MaraselServiceHolder> {
    Context context;
    ArrayList<MaraselServiceList> myList;

    public MaraselServiceAdapter(Context context, ArrayList<MaraselServiceList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MaraselServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.marasel_service_item, parent, false);
        MaraselServiceHolder maraselServiceHolder = new MaraselServiceHolder(view);
        return maraselServiceHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MaraselServiceHolder holder, int position) {
        holder.title.setText(myList.get(position).getTitle());

        Glide.with(context)
                .load(myList.get(position).getIcon()).placeholder(R.drawable.marasel_service_bg)
                .listener(new RequestListener<Drawable>  () {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.container.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(holder.icon);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(myList.get(position).getTitle(), myList.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    void replaceFragment(String title, String id) {
        Fragment Categories = new Categories();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("id", id);
        //set Fragmentclass Arguments
        Categories.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, Categories).addToBackStack(null).commit();
    }

    class MaraselServiceHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        LinearLayout item;
        ShimmerFrameLayout container;

        public MaraselServiceHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            icon = itemView.findViewById(R.id.icon);
            item = itemView.findViewById(R.id.item);
            container = itemView.findViewById(R.id.shimmer_view_container);
            container.startShimmerAnimation();
        }
    }
}
