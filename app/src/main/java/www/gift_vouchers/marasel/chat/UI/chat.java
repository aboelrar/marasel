package www.gift_vouchers.marasel.chat.UI;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Delivery;
import www.gift_vouchers.marasel.MainScreen.ui.RateDriver.UI.RateDriver;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.chat.Model.ChatList;
import www.gift_vouchers.marasel.chat.Model.UploadImage;
import www.gift_vouchers.marasel.chat.Pattern.ChatAdapter;
import www.gift_vouchers.marasel.databinding.ChatBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;
import www.gift_vouchers.marasel.utils.utils_adapter;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class chat extends Fragment implements View.OnClickListener, ChildEventListener, OnCompleteListener, OnMapReadyCallback {
    ChatBinding binding;
    ArrayList<ChatList> chatLists = new ArrayList<>();
    DatabaseReference myRef;
    ChatAdapter chatAdapter;
    File myImg;
    int requestCode = 1;
    int storage_premission_code = 1;
    ChatModelView chatModelView;
    String type = "1";
    ArrayList<String> type_list = new ArrayList<>();
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionsGranted;
    Delivery delivery;
    String status;

    public chat(Delivery delivery, String status) {
        // Required empty public constructor
        this.delivery = delivery;
        this.status = status;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.chat, container, false);
        View view = binding.getRoot();

        mLocationPermissionsGranted = new utils().getLocationPermission(getContext(), getActivity()); //MAP PERMISSION OPEN SETTINGS

        chatModelView = new ChatModelView(); //CALL MODEL VIEW

        setChatAdapter(); //CALL ADAPTER

        //SET UP REALTIME DB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Chat");

        checkVisabilityRate(); //CHECK VISABILITY FOR RATE

        //CLICK LISTNERS
        binding.uploadImg.setOnClickListener(this);
        binding.send.setOnClickListener(this);
        binding.myLocation.setOnClickListener(this);
        binding.rate.setOnClickListener(this);

        //EVENT LISTNER ON ORDER
        myRef.child("Order" + getArguments().getString("id")).orderByKey().addChildEventListener(this);

        //SET ORDER TITLE
        binding.orderNum.setText("Order" + getArguments().getString("id"));

        return view;
    }

    //CHECK RATE VISABILITY
    void checkVisabilityRate() {
        if (!status.equals("1")) {
            binding.rate.setVisibility(View.GONE);
        }
    }

    //SET ADAPTER
    void setChatAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatList.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(chatLists, getContext(), R.layout.msg_item_left
                , getArguments().getString("img"), delivery);
        chatAdapter.notifyDataSetChanged();
        binding.chatList.setAdapter(chatAdapter);
    }

    //SET REALTIME DATABASE
    void setData(String url, String type, double lat, double lng) {
        Date currentTime = Calendar.getInstance().getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("image", url);
        map.put("lat", "" + lat);
        map.put("long", "" + lng);
        map.put("messageText", binding.writeMsg.getText().toString());
        map.put("messageTime", "" + currentTime.getHours() + ":" + currentTime.getMinutes());
        map.put("type", type);
        map.put("userId", new saved_data().getId(getContext()));


        String key = myRef.push().getKey();

        myRef.child("Order" + getArguments().getString("id")).child(key).updateChildren(map);
        Log.e("total_is", "" + myRef.getKey());

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send) {
            type = "1";
            setData("", type, 0, 0);
        } else if (view.getId() == R.id.upload_img) {
            type = "2";
            grantedOrNot();
        } else if (view.getId() == R.id.my_location) {
            type = "3";
            checkPermission();
        } else if (view.getId() == R.id.rate) {
            replaceFragment(getArguments().getString("s_id")
                    , getArguments().getString("s_img"), getArguments().getString("phone"));
        }
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        binding.writeMsg.getText().clear();

        type = "" + dataSnapshot.child("type").getValue();

        chatLists.add(new ChatList("" + dataSnapshot.child("userId").getValue(),
                "" + dataSnapshot.child("image").getValue(), "" + dataSnapshot.child("messageText").getValue(),
                "" + dataSnapshot.child("messageTime").getValue(), "" + dataSnapshot.child("lat").getValue()
                , "" + dataSnapshot.child("long").getValue(), "" + dataSnapshot.child("type").getValue()));

        addItem(dataSnapshot);


    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = data.getData();

                myImg = new File(new utils().getRealPathFromURI(resizeUri(selectedImage), getContext()));

                uploadImage(); // UPLOAD IMAGE TO SERVE


            }
        }
    }

    //RESIZE FILE
    Uri resizeUri(Uri uri) {
        InputStream imageStream = null;
        try {
            FileOutputStream fos = null;
            imageStream = getContext().getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap SelectedPhoto = BitmapFactory.decodeStream(imageStream);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        SelectedPhoto.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        SelectedPhoto.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), SelectedPhoto, "IMG_" + System.currentTimeMillis(), null);

        return Uri.parse(path);
    }

    //Permission
    private void grantedOrNot() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(getContext()).setTitle("Permission To Open Gallery").setMessage("If you need to upload image you must do this premission").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                //positive
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, storage_premission_code);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(i, "Select Your Photo"), requestCode);
                }
            }).create().show();
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode);
        }
    }

    //UPLOAD IMAGE TO SERVER
    void uploadImage() {
        chatModelView.getData(myImg);
        chatModelView.MutableLiveData.observe(this, new Observer<UploadImage>() {
            @Override
            public void onChanged(UploadImage uploadImage) {
                setData(uploadImage.getData(), type, 0, 0);
            }
        });
    }

    //ADD ITEM TO THE LIST
    void addItem(DataSnapshot dataSnapshot) {
        if (type.equals("1")) {
            if (new saved_data().getId(getContext()).equals("" + dataSnapshot.child("userId").getValue())) {
                chatAdapter.addList(chatLists, R.layout.msg_item_left);
            } else {
                chatAdapter.addList(chatLists, R.layout.msg_item_right);
            }
        } else if (type.equals("2")) {
            if (new saved_data().getId(getContext()).equals("" + dataSnapshot.child("userId").getValue())) {
                chatAdapter.addList(chatLists, R.layout.img_item_left);
            } else {
                chatAdapter.addList(chatLists, R.layout.msg_item_right);
            }
        } else if (type.equals("3")) {
            if (new saved_data().getId(getContext()).equals("" + dataSnapshot.child("userId").getValue())) {
                chatAdapter.addList(chatLists, R.layout.map_item_left);
            } else {
                chatAdapter.addList(chatLists, R.layout.map_item_right);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Select Your Photo"), requestCode);
            } else {
                Toast.makeText(getContext(), "not Granted", Toast.LENGTH_SHORT).show();
            }
        }
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

            setData("", type, current_lat, current_lng); //SET FIREBASE DATA


        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    //REPLACE FRAGMENT
    void replaceFragment(String id, String img, String phone) {
        Fragment rateDriver = new RateDriver(delivery);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("img", img);
        bundle.putString("phone", phone);
        rateDriver.setArguments(bundle);

        ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, rateDriver).addToBackStack(null).commit();
    }

}
