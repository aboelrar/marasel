package www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.UI;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.Model.editProfileRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.PersonalInformationBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.local_data.send_data;
import www.gift_vouchers.marasel.utils.utils;

import static android.app.Activity.RESULT_OK;
import static www.gift_vouchers.marasel.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PersonalInformation extends Fragment implements View.OnClickListener, SegmentedButtonGroup.OnPositionChangedListener {
    int requestCode = 1;
    int storage_premission_code = 1;
    File myImg;
    PersonalInformationBinding binding;
    PersonalInformationModelView personalInformationModelView;
    String gender = "1";
    Datum datum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.personal_information, container, false);
        View view = binding.getRoot();

        personalInformationModelView = new PersonalInformationModelView();

        binding.username.setText(new saved_data().get_name(getContext()));
        binding.email.setText(new saved_data().get_email(getContext()));
        binding.phone.setText(new saved_data().get_phone(getContext()));
        //SET PROFILE IMAGE
        if (!new saved_data().get_picture(getContext()).equals("")) {
            Glide.with(getContext()).load(new saved_data().get_picture(getContext())).into(binding.criImg);
        }
        //SET GENDER
        if (new saved_data().get_gender(getContext()).equals("1")) {
            binding.gender.setPosition(0, true);
        } else if (new saved_data().get_gender(getContext()).equals("2")) {
            binding.gender.setPosition(1, true);
        }

        binding.uploadImg.setOnClickListener(this);
        binding.gender.setOnPositionChangedListener(this);
        binding.confirm.setOnClickListener(this);


        return view;
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.upload_img) {
            grantedOrNot();
        } else if (view.getId() == R.id.confirm) {
            new utils().set_dialog(getContext());
            editProfile();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = data.getData();

                myImg = new File(new utils().getRealPathFromURI(resizeUri(selectedImage), getContext()));

            }
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

        binding.criImg.setImageBitmap(SelectedPhoto); //SET IMAGE

        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), SelectedPhoto, "IMG_" + System.currentTimeMillis(), null);

        return Uri.parse(path);
    }

    void getData() {

        personalInformationModelView.getData("Bearer " + new saved_data().get_token(getContext()),
                binding.username.getText().toString(), binding.phone.getText().toString(),
                binding.email.getText().toString(), myImg, gender);

        //OBSERVE DATA
        personalInformationModelView.MutableLiveData.observe(this, new Observer<editProfileRoot>() {
            @Override
            public void onChanged(editProfileRoot editProfileRoot) {
                datum = editProfileRoot.getData();
                new utils().dismiss_dialog(getContext());
                send_data.set_user_img(getContext(), datum.getImage());
                send_data.set_user_gender(getContext(), "" + datum.getGender());
                Toasty.success(getContext(), "" + editProfileRoot.getMessage(), Toasty.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onPositionChanged(int position) {
        if (position == 0) {
            gender = "1";
        } else if (position == 1) {
            gender = "2";
        }
    }

    //EDIT PROFILE VALIDATION
    void editProfile() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (binding.username.getText().toString().equals("")) {
            String username_val = getResources().getString(R.string.user_val);
            binding.email.setError(username_val);
            yoyo(R.id.email, binding.email);
        } else if (binding.email.getText().toString().length() < 5 && !binding.email.getText().toString().matches(emailPattern))  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.invalid_email_address);
            binding.email.setError(username_val);
            yoyo(R.id.email, binding.email);

        } else if (binding.phone.getText().toString().length() < 11) {
            String phone_val = getResources().getString(R.string.phone);
            binding.phone.setError(phone_val);
            yoyo(R.id.phone, binding.phone);
        } else {
            getData();
        }
    }
}
