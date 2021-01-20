package www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.MakeOrder;
import www.gift_vouchers.marasel.MainScreen.ui.RateStore.UI.RateStore;
import www.gift_vouchers.marasel.MainScreen.ui.myLocation.myLocation;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.MakeOrderBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class makeOrder extends Fragment implements OnMapReadyCallback, View.OnClickListener, Callback {
    View view;
    private GoogleMap mMap;
    MakeOrderBinding binding;
    private Callback callback;
    MakeOrderModelView makeOrderModelView;
    String locationLat, locationLng;
    String timeId;
    Datum datum;

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

        //SET CLICK LISTENER
        binding.completeOrderNow.setOnClickListener(this);


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
        } else if (view.getId() == R.id.complete_order_now) {

            if (binding.address.getText().toString().equals("")) {
                Toasty.warning(getContext(), getString(R.string.enter_address), Toasty.LENGTH_SHORT).show();
            } else if (binding.deliveryTxtTime.getText().toString().equals("")) {
                Toasty.warning(getContext(), getString(R.string.enter_time), Toasty.LENGTH_SHORT).show();
            } else {
                //GET DATA
                getData();
            }

        }
    }


    @Override
    public void callbackMethod(ArrayList<String> date) {
        binding.deliveryTxtTime.setText(date.get(0));
        timeId = date.get(1);

    }

    @Override
    public void callbackAddressMethod(ArrayList<String> date) {
        binding.address.setText(date.get(0));
        locationLat = date.get(1);
        locationLng = date.get(2);
        Log.e("lat4lng", locationLat + locationLng);
    }

    //SET  DATA
    void getData() {
        String type;

        //CHECK STORE TYPE
        if (getArguments().getString("type").equals("restaurants")) {
            type = "1";
        } else {
            type = "2";
        }

        new utils().set_dialog(getContext()); //OPEN PROGRESS DIALOG

        makeOrderModelView = new MakeOrderModelView();
        makeOrderModelView.getDataMakeOrder("Bearer " + new saved_data().get_token(getContext()), locationLat, locationLng,
                timeId, "1", "100", binding.address.getText().toString(), "", type);

        //OBSERVE DATA
        makeOrderModelView.MutableLiveDataMakeOrder.observe(this, new Observer<MakeOrder>() {
            @Override
            public void onChanged(MakeOrder makeOrder) {
                datum = makeOrder.getData();

                new utils().dismiss_dialog(getContext()); //DISMISS DIALOG

                Toasty.success(getContext(), makeOrder.getMessage(), Toasty.LENGTH_SHORT).show();

                replaceFragment("" + datum.getId());
            }
        });

    }

    void replaceFragment(String id) {
        Fragment Categories = new RateStore();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        //set Fragmentclass Arguments
        Categories.setArguments(bundle);

        ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, Categories).addToBackStack(null).commit();
    }
}

