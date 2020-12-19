package www.gift_vouchers.marasel.MainScreen.ui.Store.pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Store.model.ImgList;
import www.gift_vouchers.marasel.R;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.CategoriesHolder> {
    Context context;
    ArrayList<ImgList> myList;

    public ImgAdapter(Context context, ArrayList<ImgList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.img_item,parent,false);
        CategoriesHolder categoriesHolder = new CategoriesHolder(view);
        return categoriesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesHolder holder, int position) {
     Glide.with(context).load(myList.get(position).getImg()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class CategoriesHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);

        }
    }



}
