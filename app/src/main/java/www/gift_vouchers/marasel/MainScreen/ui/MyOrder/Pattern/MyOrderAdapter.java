package www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Pattern;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.MyCartList;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.MyOrderList;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.UI.Offers;
import www.gift_vouchers.marasel.MainScreen.ui.Store.ui.Store;
import www.gift_vouchers.marasel.R;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderHolder> {
    Context context;
    ArrayList<MyOrderList> myList;

    public MyOrderAdapter(Context context, ArrayList<MyOrderList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_order_item, parent, false);
        MyOrderHolder MyOrderHolder = new MyOrderHolder(view);
        return MyOrderHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderHolder holder, int position) {
        holder.title.setText(myList.get(position).getName());
        holder.type.setText(myList.get(position).getOrderName());
        holder.code.setText(myList.get(position).getCode());
        holder.time.setText(myList.get(position).getExpectTime());
        Glide.with(context).load(myList.get(position).getProductImg()).into(holder.img);

        //SET TEXT FOR BUTTON
        if (myList.get(position).getStatus().equals("1")) {
            holder.offer_button.setText(context.getString(R.string.a_waiting_offer));
        } else if (myList.get(position).getStatus().equals("2")) {
            holder.offer_button.setText(context.getString(R.string.has_offers));
        } else if (myList.get(position).getStatus().equals("3")) {
            holder.offer_button.setText(context.getString(R.string.connecting));
        } else if (myList.get(position).getStatus().equals("4")) {
            holder.offer_button.setText(context.getString(R.string.cancelled));
        }

        //SET ON CLICK LISTENER
        holder.item.setOnClickListener(new View.OnClickListener() {
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
        LinearLayout item;

        public MyOrderHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            code = itemView.findViewById(R.id.code);
            time = itemView.findViewById(R.id.time);
            offer_button = itemView.findViewById(R.id.offer_button);
            item = itemView.findViewById(R.id.item);
        }
    }

    void replaceFragment(String id) {
        Fragment offers = new Offers();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        offers.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, offers).addToBackStack(null).commit();
    }
}
