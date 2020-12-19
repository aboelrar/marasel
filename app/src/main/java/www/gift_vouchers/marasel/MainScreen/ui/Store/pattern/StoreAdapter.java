package www.gift_vouchers.marasel.MainScreen.ui.Store.pattern;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import www.gift_vouchers.marasel.MainScreen.ui.Product.Ui.Product;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.ProductList;
import www.gift_vouchers.marasel.R;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ProductHolder> {
    Context context;
    ArrayList<ProductList> myList;
    String storeId;

    public StoreAdapter(Context context, ArrayList<ProductList> myList, String storeId) {
        this.context = context;
        this.myList = myList;
        this.storeId = storeId;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cat_item2,parent,false);
        ProductHolder ProductHolder = new ProductHolder(view);
        return ProductHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
     Glide.with(context).load(myList.get(position).getIcon()).into(holder.productImg);
     holder.title.setText(myList.get(position).getName());

     holder.item.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             replaceFragment(myList.get(position).getId());

             Log.e("eeee",""+ myList.get(position).getId()+ "..." + storeId);

         }
     });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        CircleImageView productImg;
        TextView title;
        LinearLayout item;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.productImg);
            title = itemView.findViewById(R.id.title);
            item = itemView.findViewById(R.id.item);

        }
    }

    void replaceFragment(String id)
    {
        Fragment Categories = new Product();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("storeId", storeId);

        //set Fragmentclass Arguments
        Categories.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, Categories).addToBackStack(null).commit();
    }

}
