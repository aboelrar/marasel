package www.gift_vouchers.marasel.MainScreen.ui.Cart.Pattern;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.DeleteProductRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.MyCartList;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Ui.Callback;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Ui.CartModelView;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.CategoryList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.ui.Store;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyCartHolder> {
    Context context;
    ArrayList<MyCartList> myList;
    int quantityNum;
    int totalPrice;
    CartModelView cartModelView;
    Callback callback;

    public MyCartAdapter(Context context, ArrayList<MyCartList> myList, CartModelView cartModelView, Callback callback) {
        this.context = context;
        this.myList = myList;
        this.cartModelView = cartModelView;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_cart_item, parent, false);
        MyCartHolder MyCartHolder = new MyCartHolder(view);
        return MyCartHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartHolder holder, int position) {
        quantityNum = Integer.parseInt(myList.get(position).getQuantity());
        holder.title.setText(myList.get(position).getName());
        holder.price.setText(myList.get(position).getPrice());
        holder.price.setText(myList.get(position).gettPrice());
        holder.quantity.setText(myList.get(position).getQuantity());
        holder.quantityNum.setText(myList.get(position).getQuantity());
        Glide.with(context).load(myList.get(position).getImg()).into(holder.productImg);

        //SET INCREASE
        holder.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityNum++;
                holder.quantity.setText("" + quantityNum); //SET QUANTITY PRICE
                holder.quantityNum.setText("" + quantityNum); //SET QUANTITY PRICE

                //SET TOTAL PRICE
                totalPrice = quantityNum * Integer.parseInt(myList.get(position).gettPrice()); //GET TOTAL PRICE
                holder.tPrice.setText("" + totalPrice + " " + context.getString(R.string.egp)); //SET TOTAL PRICE
            }
        });

        //SET dCREASE
        holder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantityNum == 1) {

                    Toasty.warning(context, context.getString(R.string.quantityCantBeLess), Toasty.LENGTH_SHORT).show();
                } else {
                    quantityNum--;
                    holder.quantity.setText("" + quantityNum); //SET QUANTITY PRICE

                    //SET TOTAL PRICE
                    totalPrice = quantityNum * Integer.parseInt(myList.get(position).gettPrice()); //GET TOTAL PRICE
                    holder.tPrice.setText("" + totalPrice + " " + context.getString(R.string.egp)); //SET TOTAL PRICE

                }
            }
        });

        //Delete element
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog(position, myList.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class MyCartHolder extends RecyclerView.ViewHolder {
        ImageView productImg, delete;
        TextView title, price, inc, dec, quantity, quantityNum, tPrice;

        public MyCartHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.productImg);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            inc = itemView.findViewById(R.id.inc);
            dec = itemView.findViewById(R.id.dec);
            quantity = itemView.findViewById(R.id.quantity);
            quantityNum = itemView.findViewById(R.id.quantity_num);
            tPrice = itemView.findViewById(R.id.totalPrice);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    //OPEN ALERT DIALOG
    void alertDialog(int position, String id) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.delete_item))
                .setMessage(context.getString(R.string.are_you_sure_delete))

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //OPEN DIALOG
                        new utils().set_dialog(context);
                        //DELETE ITEM FROM DB
                        deleteItemFromDB(id, position);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    //DELETE ITEM FROM DB
    void deleteItemFromDB(String id, int position) {
        cartModelView.deleteData("Bearer " + new saved_data().get_token(context), id);

        //OBSERVE DATA
        cartModelView.MutableLiveDataDelete.observe(((AppCompatActivity) context), new Observer<DeleteProductRoot>() {
            @Override
            public void onChanged(DeleteProductRoot deleteProductRoot) {
                //STOP DIALOG
                new utils().dismiss_dialog(context);
                //REMOVE ITEM
                myList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, myList.size());
                //MESSAGE
                Toasty.success(context, deleteProductRoot.getMessage(), Toasty.LENGTH_SHORT).show();

                //CHECK IF LIST SIZE ZERO OR NOT
                if (myList.size() == 0) {
                    callback.callbackMethod();
                }
            }
        });
    }

}
