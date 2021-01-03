package www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.MyCartList;
import www.gift_vouchers.marasel.R;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderHolder> {
    Context context;
    ArrayList<MyCartList> myList;
    int quantityNum;
    int totalPrice;

    public MyOrderAdapter(Context context, ArrayList<MyCartList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_order_item,parent,false);
        MyOrderHolder MyOrderHolder = new MyOrderHolder(view);
        return MyOrderHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class MyOrderHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, type, code, time;
        public MyOrderHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            code = itemView.findViewById(R.id.code);
            time = itemView.findViewById(R.id.time);
        }
    }


}
