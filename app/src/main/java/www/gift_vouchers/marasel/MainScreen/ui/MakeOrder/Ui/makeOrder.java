package www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.renderscript.Double2;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.Category;
import www.gift_vouchers.marasel.MainScreen.ui.myLocation.myLocation;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.MakeOrderBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class makeOrder extends Fragment implements OnMapReadyCallback, View.OnClickListener,Callback{
    View view;
    private GoogleMap mMap;
    MakeOrderBinding binding;
    private Callback callback;

    public makeOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.make_order, container, false);
        View view = binding.getRoot();

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        callback = this;

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Double lat = Double.valueOf(new saved_data().getStoreLat(getContext()));
        Double lng = Double.valueOf(new saved_data().getStoreLng(getContext()));

        MarkerOptions marker_winch = new MarkerOptions().position(new LatLng(lat, lng)).title(new saved_data().getStoreTitle(getContext()));
        mMap.addMarker(marker_winch);

        //ZOOM CAMERA
        camZoom(lat, lng);
    }

    public void camZoom(Double lat, Double lng) {
        //MOVE CAMERA
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(lat, lng))
                .zoom(20)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.orderTitle.setText(new saved_data().getStoreTitle(getContext()));
        Glide.with(getContext()).load(new saved_data().getStoreImg(getContext())).into(binding.catLogo);

        binding.deliveryPlace.setOnClickListener(this);
        binding.deliveryTime.setOnClickListener(this);

        //SET ADDRESS TEXT




    }

    @Override
    public void onClick(View view) {
        if ((view.getId() == R.id.delivery_place)) {
            Fragment home = new myLocation(callback);
            ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                    .add(R.id.frag, home).addToBackStack(null).commit();
        } else if (view.getId() == R.id.delivery_time) {
            DeliveryTime DeliveryTime = new DeliveryTime(callback);
            DeliveryTime.show(getActivity().getSupportFragmentManager(), "doc_list");
        }
    }


    @Override
    public void callbackMethod(String date) {
        binding.deliveryTxtTime.setText(date);

    }

    @Override
    public void callbackAddressMethod(String date) {
        binding.address.setText(date);
    }
}

