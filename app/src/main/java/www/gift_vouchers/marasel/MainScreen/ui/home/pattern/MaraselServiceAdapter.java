package www.gift_vouchers.marasel.MainScreen.ui.home.pattern;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.home.model.MaraselServiceList;
import www.gift_vouchers.marasel.R;

public class MaraselServiceAdapter extends RecyclerView.Adapter<MaraselServiceAdapter.MaraselServiceHolder> {
    Context context;
    ArrayList<MaraselServiceList> myList;

    public MaraselServiceAdapter(Context context, ArrayList<MaraselServiceList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MaraselServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.marasel_service_item,parent,false);
        MaraselServiceHolder maraselServiceHolder = new MaraselServiceHolder(view);
        return maraselServiceHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MaraselServiceHolder holder, int position) {
       holder.title.setText(myList.get(position).getTitle());
        Glide.with(context).load(myList.get(position).getIcon()).into(holder.icon);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             holder.title.setTextColor(Color.WHITE);
//             holder.icon.setColorFilter(context.getResources().getColor(R.color.f_color));
////             holder.icon.setSupportBackgroundTintList(ContextCompat.getColorStateList(context, R.color.f_color));

            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class MaraselServiceHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        LinearLayout item;
        public MaraselServiceHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            icon = itemView.findViewById(R.id.icon);
            item = itemView.findViewById(R.id.item);
        }
    }
}
