package www.gift_vouchers.marasel.chat.Pattern;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.CategoryList;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.Image;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Delivery;
import www.gift_vouchers.marasel.MainScreen.ui.RateDriver.UI.RateDriver;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.chat.Model.ChatList;
import www.gift_vouchers.marasel.local_data.saved_data;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> implements OnMapReadyCallback {
    ArrayList<ChatList> myList;
    Context context;
    int layout;
    GoogleMap map;
    double lat, lng;
    String secondImg;
    Delivery delivery;

    public ChatAdapter(ArrayList<ChatList> myList, Context context, int layout, String secondImg, Delivery delivery) {
        this.myList = myList;
        this.context = context;
        this.layout = layout;
        this.secondImg = secondImg;
        this.delivery = delivery;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        ChatHolder chatHolder = new ChatHolder(view);
        return chatHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        if ((layout == R.layout.msg_item_left) || (layout == R.layout.msg_item_right)) {
            if (layout == R.layout.msg_item_left) {
                Glide.with(context).load(new saved_data().get_picture(context)).into(holder.img);
            } else {
                Glide.with(context).load(secondImg).into(holder.img);
            }
            holder.msg.setText(myList.get(position).getMsg());
        } else if ((layout == R.layout.img_item_left) || (layout == R.layout.img_item_right)) {
            if (layout == R.layout.img_item_left) {
                Glide.with(context).load(new saved_data().get_picture(context)).into(holder.img);
            } else {
                Glide.with(context).load(secondImg).into(holder.img);
            }
            Glide.with(context).load(myList.get(position).getImg()).into(holder.imageView);
        } else {

            if (layout == R.layout.map_item_left) {
                Glide.with(context).load(new saved_data().get_picture(context)).into(holder.img);
            } else {
                Glide.with(context).load(secondImg).into(holder.img);
            }
            // Initialise the MapView
            holder.mapView.onCreate(null);
            holder.mapView.onResume();  //Probably U r missing this
            holder.mapView.getMapAsync(this);

            lat = Double.parseDouble(myList.get(position).getLat());
            lng = Double.parseDouble(myList.get(position).getLng());
        }
        holder.time.setText(myList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(context);
        map = googleMap;

        //MOVE CAMERA
        camZoom(lat, lng);

        //SET MARKER
        map.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng)));
    }

    class ChatHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView msg, time;
        ImageView imageView;
        MapView mapView;
        View mMap;
        ImageView rate;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            msg = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img_msg);
            mapView = itemView.findViewById(R.id.map);
            rate = itemView.findViewById(R.id.rate);
        }
    }

    public void addList(ArrayList<ChatList> mylists, int layout) {
        this.layout = layout;
//        myList.addAll(mylists);
        for (int i = 0; i < mylists.size(); i++) {
            notifyItemInserted(mylists.size() - 1);
        }
    }

    public void camZoom(Double lat, Double lng) {
        //MOVE CAMERA
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(lat, lng))
                .zoom(20)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


}
