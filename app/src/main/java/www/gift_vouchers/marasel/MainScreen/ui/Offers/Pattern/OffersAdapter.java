package www.gift_vouchers.marasel.MainScreen.ui.Offers.Pattern;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.MyOrderList;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.OfferList;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.UI.OffersModelView;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersHolder> {
    Context context;
    ArrayList<OfferList> myList;
    OffersModelView offersModelView;
    String orderId;

    public OffersAdapter(Context context, ArrayList<OfferList> myList, OffersModelView offersModelView, String orderId) {
        this.context = context;
        this.myList = myList;
        this.offersModelView = offersModelView;
        this.orderId = orderId;
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

        //Accept Order
        holder.acceptOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DIALOG
                new utils().set_dialog(context);

                offersModelView.AcceptOrReject("Bearer " + new saved_data().get_token(context), orderId,
                        myList.get(position).getId(), "1");
            }
        });

        //Reject Order
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(position);
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

    //OPEN ALERT DIALOG
    void alertDialog(int position) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.delete_item))
                .setMessage(context.getString(R.string.are_you_sure_delete))

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //OPEN DIALOG
                        new utils().set_dialog(context);

                        offersModelView.AcceptOrReject("Bearer " + new saved_data().get_token(context), orderId,
                                myList.get(position).getId(), "2");
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
