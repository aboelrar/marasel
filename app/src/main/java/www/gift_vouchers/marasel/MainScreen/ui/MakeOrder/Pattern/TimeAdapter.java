package www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Pattern;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.MyCartList;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.TimeList;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Ui.Callback;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Ui.DeliveryTime;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.local_data.send_data;
import www.gift_vouchers.marasel.utils.utils;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.DeliveryPlaceHolder> {
    Context context;
    ArrayList<TimeList> myList;
    BottomSheetDialogFragment bottomSheetDialogFragment;
    Callback callback;


    public TimeAdapter(Context context, ArrayList<TimeList> myList, BottomSheetDialogFragment bottomSheetDialogFragment,Callback callback) {
        this.context = context;
        this.myList = myList;
        this.bottomSheetDialogFragment = bottomSheetDialogFragment;
        this.callback = callback;
    }

    @NonNull
    @Override
    public DeliveryPlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_time_item, parent, false);
        DeliveryPlaceHolder DeliveryPlaceHolder = new DeliveryPlaceHolder(view);
        return DeliveryPlaceHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryPlaceHolder holder, int position) {
        holder.time.setText(myList.get(position).getTitle());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SET DELIVERY TIME
                callback.callbackMethod(myList.get(position).getTitle());

                //DISMISS FRAGMENT
                bottomSheetDialogFragment.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class DeliveryPlaceHolder extends RecyclerView.ViewHolder {
        TextView time;
        LinearLayout item;

        public DeliveryPlaceHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            item = itemView.findViewById(R.id.item);
        }
    }


}
