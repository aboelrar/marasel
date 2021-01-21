package www.gift_vouchers.marasel.MainScreen.ui.Offers.Pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.MyOrderList;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.OfferList;
import www.gift_vouchers.marasel.R;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersHolder> {
    Context context;
    ArrayList<OfferList> myList;

    public OffersAdapter(Context context, ArrayList<OfferList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public OffersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.offers_item, parent, false);
        OffersHolder OffersHolder = new OffersHolder(view);
        return OffersHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OffersHolder holder, int position) {
        holder.title.setText(myList.get(position).getName());
        holder.stars.setText(myList.get(position).getStar());
        holder.desc.setText(myList.get(position).getDesc());
        Glide.with(context).load(myList.get(position).getProductImg()).into(holder.img);

        holder.acceptOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class OffersHolder extends RecyclerView.ViewHolder {
        TextView title, stars, desc;
        ImageView img, delete;
        Button acceptOffer;

        public OffersHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.rate);
            desc = itemView.findViewById(R.id.offer_txt);
            img = itemView.findViewById(R.id.img);
            delete = itemView.findViewById(R.id.reject_offer_button);
            acceptOffer = itemView.findViewById(R.id.accept_offer_button);
        }
    }


}
