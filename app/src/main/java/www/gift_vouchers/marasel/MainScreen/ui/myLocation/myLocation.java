package www.gift_vouchers.marasel.MainScreen.ui.myLocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Ui.makeOrder;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.MyLocationBinding;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class myLocation extends Fragment implements OnMapReadyCallback, OnCompleteListener, GoogleMap.OnCameraChangeListener, View.OnClickListener {
    MyLocationBinding binding;
    View view;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionsGranted;


    public myLocation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.my_location, container, false);
        View view = binding.getRoot();

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mLocationPermissionsGranted = new utils().getLocationPermission(getContext(),getActivity()); //MAP PERMISSION OPEN SETTINGS

        checkPermission(); //CHECk PERMISSION

        mGoogleMap.setOnCameraChangeListener(this); //CHANGE CAMERA POSITION


    }


    //IF GRANTED PERMISSION DONE GET CURRENT LOCATION
    public void checkPermission() {
        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

        }
    }

    //GET CURRENT LOCATION
    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        try {

            final Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(this);


        } catch (SecurityException e) {

        }
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful() && task.getResult() != null) {

            Location currentLocation = (Location) task.getResult();
            double current_lat = currentLocation.getLatitude();
            double current_lng = currentLocation.getLongitude();

            //MOVE CAMERA
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(current_lat, current_lng)));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            //SET LOCATION ADDRESS
            getAddress(current_lat, current_lng);

            mGoogleMap.clear();
        }
    }

    //GET ALL OF THINGS ABOUT MY LOCATION INFORMATION
    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);

            if (addresses.size() != 0) {
                Address obj = addresses.get(0);
                String address_name = obj.getAddressLine(0);
                binding.address.setText(address_name); //SET ADDRESS
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        double lat = cameraPosition.target.latitude;
        double lng = cameraPosition.target.longitude;
        getAddress(lat, lng);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_delivery_place)
        {
            if (binding.noHomeFlat.getText().toString().equals(""))
            {
                Toasty.warning(getContext(),getString(R.string.enter_flat),Toasty.LENGTH_SHORT).show();
            }
            else {
                replaceFragment(binding.address.getText().toString(),
                        binding.noHomeFlat.getText().toString());
            }
        }
    }

    void replaceFragment(String address, String flat_no)
    {
        Fragment makeOrder = new makeOrder();
        Bundle bundle = new Bundle();
        bundle.putString("address", address);
        bundle.putString("flat_no", flat_no);


        //set Fragmentclass Arguments
        makeOrder.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, makeOrder).addToBackStack(null).commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.confirmDeliveryPlace.setOnClickListener(this);
    }
}