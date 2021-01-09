package www.gift_vouchers.marasel.Drivers.UI.DriverInfo.UI;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.grpc.Compressor;
import www.gift_vouchers.marasel.Drivers.UI.DriverInfo.Model.DriverInfoRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.DriverInfoBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.local_data.send_data;
import www.gift_vouchers.marasel.utils.utils;

import static android.app.Activity.RESULT_OK;
import static www.gift_vouchers.marasel.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverInfo extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    int storage_premission_code = 1;
    DriverInfoBinding binding;
    int requestCode = 0;
    DriverInfoModelView driverInfoModelView;
    File idCopyFile, frontFile, backFile, licenseFile;

    public DriverInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.driver_info, container, false);
        View view = binding.getRoot();

        binding.switcher.setOnCheckedChangeListener(this);

        driverInfoModelView = new DriverInfoModelView();

        getData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.idCopy.setOnClickListener(this);
        binding.frontVehicle.setOnClickListener(this);
        binding.backVehicle.setOnClickListener(this);
        binding.license.setOnClickListener(this);
        binding.apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_copy) {
            requestCode = 1;
            grantedOrNot();
        } else if (view.getId() == R.id.license) {
            requestCode = 4;
            grantedOrNot();
        } else if (view.getId() == R.id.front_vehicle) {
            requestCode = 2;
            grantedOrNot();
        } else if (view.getId() == R.id.back_vehicle) {
            requestCode = 3;
            grantedOrNot();
        } else if (view.getId() == R.id.apply) {
            new utils().set_dialog(getContext());
            sendData();
        }
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

    //GET DATA
    void getData() {
        driverInfoModelView.MutableLiveData.observe(this, new Observer<DriverInfoRoot>() {
            @Override
            public void onChanged(DriverInfoRoot driverInfoRoot) {
                new utils().dismiss_dialog(getContext());

                DriverInfoAccept driverInfoAccept = new DriverInfoAccept();
                driverInfoAccept.dialog(getContext(), R.layout.driver_info_accept, .90);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = data.getData();

                idCopyFile = new File(new utils().getRealPathFromURI(resizeUri(selectedImage), getContext()));

                binding.idCopy.setText(getString(R.string.img_uploaded));
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();

                frontFile = new File(new utils().getRealPathFromURI(resizeUri(selectedImage), getContext()));
                binding.frontVehicle.setText(getString(R.string.img_uploaded));
            } else if (requestCode == 3) {
                Uri selectedImage = data.getData();

                backFile = new File(new utils().getRealPathFromURI(resizeUri(selectedImage), getContext()));
                binding.backVehicle.setText(getString(R.string.img_uploaded));
            } else if (requestCode == 4) {
                Uri selectedImage = data.getData();

                licenseFile = new File(new utils().getRealPathFromURI(resizeUri(selectedImage), getContext()));
                binding.license.setText(getString(R.string.img_uploaded));
            }
        }
    }


    //SEND DATA
    void sendData() {
        if (binding.name.getText().toString().equals("")) {
            String username_val = getResources().getString(R.string.driver_name);
            binding.name.setError(username_val);
            yoyo(R.id.name, binding.name);
        } else if (binding.idCopy.getText().toString().equals("")) {
            String idCopy = getResources().getString(R.string.id_copy);
            binding.idCopy.setError(idCopy);
            yoyo(R.id.id_copy, binding.idCopy);
        } else if (binding.license.getText().toString().equals("")) {
            String license = getResources().getString(R.string.driver_license);
            binding.license.setError(license);
            yoyo(R.id.license, binding.license);
        } else if (binding.frontVehicle.getText().toString().equals("")) {
            String frontVehicle = getResources().getString(R.string.driver_front);
            binding.frontVehicle.setError(frontVehicle);
            yoyo(R.id.front_vehicle, binding.frontVehicle);
        } else if (binding.backVehicle.getText().toString().equals("")) {
            String backVehicle = getResources().getString(R.string.driver_back);
            binding.backVehicle.setError(backVehicle);
            yoyo(R.id.back_vehicle, binding.backVehicle);
        } else if (binding.bankType.getText().toString().equals("")) {
            String bankType = getResources().getString(R.string.driver_bank_type);
            binding.bankType.setError(bankType);
            yoyo(R.id.bank_type, binding.bankType);
        } else if (binding.bankNumber.getText().toString().equals("")) {
            String bankNumber = getResources().getString(R.string.driver_bank_number);
            binding.bankNumber.setError(bankNumber);
            yoyo(R.id.bank_number, binding.bankNumber);
        } else {
            driverInfoModelView.getData("Bearer " + new saved_data().get_token(getContext()),
                    binding.bankNumber.getText().toString(), binding.bankType.getText().toString(),
                    backFile, frontFile, licenseFile, idCopyFile,
                    binding.name.getText().toString(), "30.109760", "31.247240");
        }

    }

    /**
     * RESIZE URI
     */

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
        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), SelectedPhoto, null, null);
        return Uri.parse(path);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        send_data.checkActiveStar(getContext(), false); //SET CHECK FALSE
        getActivity().onBackPressed(); //BACK TO PREVIOUS FRAGMENT

    }
}
