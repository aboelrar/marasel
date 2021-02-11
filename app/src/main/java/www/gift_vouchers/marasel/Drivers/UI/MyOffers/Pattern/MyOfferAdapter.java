package www.gift_vouchers.marasel.Drivers.UI.MyOffers.Pattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import www.gift_vouchers.marasel.Drivers.UI.AddOffer.UI.AddOffer;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.OrderList;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.MyOfferList;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.chat.UI.chat;

public class MyOfferAdapter extends RecyclerView.Adapter<MyOfferAdapter.MyOfferHolder> {
    Context context;
    ArrayList<MyOfferList> myList;

    public MyOfferAdapter(Context context, ArrayList<MyOfferList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyOfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_offer_item, parent, false);
        MyOfferHolder MyOfferHolder = new MyOfferHolder(view);
        return MyOfferHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOfferHolder holder, int position) {
        holder.product.setText(myList.get(position).getProduct());
        holder.address.setText(myList.get(position).getAddress());
        holder.time.setText(myList.get(position).getTime());

        if (myList.get(position).getStatus().equals("0")) {
            holder.status.setText(context.getString(R.string.pending));
        } else if (myList.get(position).getStatus().equals("1")) {
            holder.status.setText(context.getString(R.string.accepted));
            holder.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(myList.get(position).getId(), myList.get(position).getClientImg());
                }
            });

        } else if (myList.get(position).getStatus().equals("2")) {
            holder.status.setText(context.getString(R.string.rejected));
        }


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Glide.with(context)
                .load(myList.get(position).getIcon())
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
                .into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class MyOfferHolder extends RecyclerView.ViewHolder {
        TextView product, address, time;
        ImageView icon;
        ShimmerFrameLayout container;
        LinearLayout item;
        Button status;

        public MyOfferHolder(@NonNull View itemView) {
            super(itemView);
            product = itemView.findViewById(R.id.product);
            address = itemView.findViewById(R.id.address);
            time = itemView.findViewById(R.id.time);
            icon = itemView.findViewById(R.id.img);
            item = itemView.findViewById(R.id.item);
            status = itemView.findViewById(R.id.status);
            container = itemView.findViewById(R.id.shimmer_view_container);
            container.startShimmerAnimation();
        }
    }

    //REPLACE FRAGMENT
    void replaceFragment(String orderId, String secondImg) {
        Fragment chat = new chat(null, "2");
        Bundle bundle = new Bundle();
        bundle.putString("id", orderId);
        bundle.putString("img", secondImg);
        chat.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, chat).addToBackStack(null).commit();
    }


}
