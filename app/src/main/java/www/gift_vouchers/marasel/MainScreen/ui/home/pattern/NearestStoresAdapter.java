package www.gift_vouchers.marasel.MainScreen.ui.home.pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.NearestStoresList;
import www.gift_vouchers.marasel.R;

public class NearestStoresAdapter extends RecyclerView.Adapter<NearestStoresAdapter.NearestStoresHolder> {
    Context context;
    ArrayList<NearestStoresList> myList;

    public NearestStoresAdapter(Context context, ArrayList<NearestStoresList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public NearestStoresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.strore_item,parent,false);
        NearestStoresHolder nearestStoresHolder = new NearestStoresHolder(view);
        return nearestStoresHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NearestStoresHolder holder, int position) {
       Glide.with(context).load(myList.get(position).getIcon()).into(holder.imageView);
       holder.title.setText(myList.get(position).getName());
       holder.type.setText(myList.get(position).getType());
       holder.distance.setText(myList.get(position).getLocation());
       holder.rate.setText(myList.get(position).getRate());

       if (myList.get(position).getFreeDelivery().equals("1"))
       {
           holder.free_delivery.setVisibility(View.GONE);
       }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class NearestStoresHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView title, type, rate, distance;
        Button free_delivery;

        public NearestStoresHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.store_item);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            rate = itemView.findViewById(R.id.rate);
            distance = itemView.findViewById(R.id.distance);
            free_delivery = itemView.findViewById(R.id.free_delivery);

        }
  }
}
