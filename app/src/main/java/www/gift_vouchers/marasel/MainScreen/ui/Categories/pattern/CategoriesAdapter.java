package www.gift_vouchers.marasel.MainScreen.ui.Categories.pattern;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.CategoryList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.ui.Store;
import www.gift_vouchers.marasel.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder> {
    Context context;
    ArrayList<CategoryList> myList;

    public CategoriesAdapter(Context context, ArrayList<CategoryList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cat_item,parent,false);
        CategoriesHolder categoriesHolder = new CategoriesHolder(view);
        return categoriesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesHolder holder, int position) {
     holder.title.setText(myList.get(position).getTitle());
     holder.type.setText(myList.get(position).getType());
     holder.distance.setText(myList.get(position).getDistance());
     holder.rate.setText(myList.get(position).getRate());
     Glide.with(context).load(myList.get(position).getImg()).into(holder.img);

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

    class CategoriesHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, type, distance, rate;
        LinearLayout item;
        public CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            distance = itemView.findViewById(R.id.distance);
            rate = itemView.findViewById(R.id.rate);
            item = itemView.findViewById(R.id.item);
        }
    }

    public void addList(ArrayList<CategoryList> mylists)
    {
//        myList.addAll(mylists);
        for(int i = 0;i<mylists.size();i++)
        {

            notifyItemInserted(mylists.size()-1);
        }
    }

    void replaceFragment(String id)
    {
        Fragment Categories = new Store();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        //set Fragmentclass Arguments
        Categories.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, Categories).addToBackStack(null).commit();
    }
}
