package www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Pattern;

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
        Glide.with(context).load(myList.get(position).getResImg()).into(holder.img);

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

        public MyOrderHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            code = itemView.findViewById(R.id.code);
            time = itemView.findViewById(R.id.time);
            offer_button = itemView.findViewById(R.id.submit_offer);
        }
    }

    void replaceFragment(String id) {
        Fragment Categories = new Store();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        //set Fragment class Arguments
        Categories.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, Categories).addToBackStack(null).commit();
    }

}
