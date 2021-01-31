package www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Pattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.MyOrderList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.ui.Store;
import www.gift_vouchers.marasel.R;

public class AvailableOrderAdapter extends RecyclerView.Adapter<AvailableOrderAdapter.MyOrderHolder> {
    Context context;
    ArrayList<OrderList> myList;

    public AvailableOrderAdapter(Context context, ArrayList<OrderList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.available_order_item, parent, false);
        MyOrderHolder MyOrderHolder = new MyOrderHolder(view);
        return MyOrderHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderHolder holder, int position) {
        holder.title.setText(myList.get(position).getResTitle());
        holder.type.setText(myList.get(position).getType());
        holder.code.setText(myList.get(position).getCode());
        holder.time.setText(myList.get(position).getTime());

        Glide.with(context)
                .load(myList.get(position).getResImg())
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


        holder.offer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(myList.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class MyOrderHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, type, code, time;
        Button offer_button;
        ShimmerFrameLayout container;


        public MyOrderHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            code = itemView.findViewById(R.id.code);
            time = itemView.findViewById(R.id.time);
            offer_button = itemView.findViewById(R.id.submit_offer);
            container = itemView.findViewById(R.id.shimmer_view_container);
            container.startShimmerAnimation();
        }
    }

    void replaceFragment(String id) {
        Fragment addOffer = new AddOffer();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        //set Fragment class Arguments
        addOffer.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, addOffer).addToBackStack(null).commit();
    }

}
