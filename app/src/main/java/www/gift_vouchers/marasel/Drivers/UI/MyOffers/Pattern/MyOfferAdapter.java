package www.gift_vouchers.marasel.Drivers.UI.MyOffers.Pattern;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.marasel.Drivers.UI.AddOffer.UI.AddOffer;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.OrderList;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.MyOfferList;
import www.gift_vouchers.marasel.R;

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
        Glide.with(context).load(myList.get(position).getIcon()).into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class MyOfferHolder extends RecyclerView.ViewHolder {
        TextView product, address, time;
        ImageView icon;

        public MyOfferHolder(@NonNull View itemView) {
            super(itemView);
            product = itemView.findViewById(R.id.product);
            address = itemView.findViewById(R.id.address);
            time = itemView.findViewById(R.id.time);
            icon = itemView.findViewById(R.id.img);
        }
    }


}
