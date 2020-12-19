package www.gift_vouchers.marasel.MainScreen.ui.Product.Pattern;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Ui.ProductDetails;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.ProductList;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.utils.utils;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    Context context;
    ArrayList<ProductList> myList;

    public ProductAdapter(Context context, ArrayList<ProductList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        ProductHolder ProductHolder = new ProductHolder(view);
        return ProductHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
     Glide.with(context).load(myList.get(position).getIcon()).into(holder.productImg);
     holder.title.setText(myList.get(position).getName());
     holder.price.setText(myList.get(position).getPrice());

     holder.item.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             replaceFragment(myList.get(position).getId());
         }
     });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        CircleImageView productImg;
        TextView title,price;
        LinearLayout item;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.productImg);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            item = itemView.findViewById(R.id.item);
        }
    }


    void replaceFragment(String id)
    {
        Fragment Categories = new ProductDetails();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);

        //set Fragmentclass Arguments
        Categories.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, Categories).addToBackStack(null).commit();
    }

}
