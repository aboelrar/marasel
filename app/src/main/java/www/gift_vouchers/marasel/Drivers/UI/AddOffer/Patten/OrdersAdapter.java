package www.gift_vouchers.marasel.Drivers.UI.AddOffer.Patten;

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

import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.OrdersList;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.OrderList;
import www.gift_vouchers.marasel.R;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyOrderHolder> {
    Context context;
    ArrayList<OrdersList> myList;

    public OrdersAdapter(Context context, ArrayList<OrdersList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        MyOrderHolder MyOrderHolder = new MyOrderHolder(view);
        return MyOrderHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderHolder holder, int position) {
        holder.title.setText(myList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class MyOrderHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyOrderHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.order_item);
        }
    }


}
