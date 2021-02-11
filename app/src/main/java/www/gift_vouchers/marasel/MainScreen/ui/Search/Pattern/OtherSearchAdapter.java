package www.gift_vouchers.marasel.MainScreen.ui.Search.Pattern;

import android.content.Context;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.CategoryList;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.SearchList;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.otherSearchList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.ui.Store;
import www.gift_vouchers.marasel.R;

public class OtherSearchAdapter extends RecyclerView.Adapter<OtherSearchAdapter.OtherResultHolder> {
    Context context;
    ArrayList<otherSearchList> myList;

    public OtherSearchAdapter(Context context, ArrayList<otherSearchList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public OtherResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.other_search_result, parent, false);
        OtherResultHolder otherResultHolder = new OtherResultHolder(view);
        return otherResultHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OtherResultHolder holder, int position) {
        Glide.with(context)
                .load(R.drawable.secondary_logo).placeholder(R.drawable.marasel_service_bg)
                .listener(new RequestListener<Drawable>() {
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
                .into(holder.img);

        holder.title.setText(myList.get(position).getTitle());
        holder.address.setText(myList.get(position).getAddress());

        //CHECK STATUS
        if (myList.get(position).getStatus() == true) {
            holder.status.setText(context.getString(R.string.open));
        } else {
            holder.status.setText(context.getString(R.string.close));
        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class OtherResultHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, address, status;
        LinearLayout item;
        ShimmerFrameLayout container;

        public OtherResultHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            address = itemView.findViewById(R.id.address);
            status = itemView.findViewById(R.id.status);
            item = itemView.findViewById(R.id.item);
            container = itemView.findViewById(R.id.shimmer_view_container);
            container.startShimmerAnimation();
        }
    }

    public void addList(ArrayList<CategoryList> mylists) {
//        myList.addAll(mylists);
        for (int i = 0; i < mylists.size(); i++) {

            notifyItemInserted(mylists.size() - 1);
        }
    }

    void replaceFragment(String id) {
        Fragment Categories = new Store();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        //set Fragmentclass Arguments
        Categories.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, Categories).addToBackStack(null).commit();
    }
}
