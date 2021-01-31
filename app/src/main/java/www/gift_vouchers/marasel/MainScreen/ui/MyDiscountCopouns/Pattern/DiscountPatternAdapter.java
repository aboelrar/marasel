package www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.Pattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.CategoryList;
import www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.Model.MyDiscountList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.ui.Store;
import www.gift_vouchers.marasel.R;

public class DiscountPatternAdapter extends RecyclerView.Adapter<DiscountPatternAdapter.DiscountCodeHolder> {
    Context context;
    ArrayList<MyDiscountList> myList;

    public DiscountPatternAdapter(Context context, ArrayList<MyDiscountList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public DiscountCodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_coupon_item,parent,false);
        DiscountCodeHolder discountCodeHolder = new DiscountCodeHolder(view);
        return discountCodeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountCodeHolder holder, int position) {
        holder.title.setText(myList.get(position).getName());
        Glide.with(context).load(myList.get(position).getImg()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class DiscountCodeHolder extends RecyclerView.ViewHolder {
        TextView title;
        CircleImageView image;
        public DiscountCodeHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.discount_title);
            image = itemView.findViewById(R.id.discount_img);

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
