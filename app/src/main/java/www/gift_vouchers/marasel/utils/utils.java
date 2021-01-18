package www.gift_vouchers.marasel.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import www.gift_vouchers.marasel.AuthScreens.Model.Datum;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.local_data.send_data;


public class utils {
    Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    static ProgressDialog pd;


    /**
     * SPLASH SCREEN
     */
    public void splash_screen(final Context context, final Class second_class) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    // sleep during 800ms
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // start HomeActivity
                Intent intent = new Intent(context, second_class);
                ((AppCompatActivity) context).overridePendingTransition(0, 0);
                context.startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(0, 0);
                ((AppCompatActivity) context).finish();
            }
        }).start();
    }

    /**
     * Upload Image
     */
    public void upload_image(Context context, int requestCode) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((AppCompatActivity) context).startActivityForResult(Intent.createChooser(i, "Select Your Photo"), requestCode);
    }

    /**
     * Upload Files
     */
    public void upload_files(Context context, int requestCode) {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.setType("application/pdf");
        i.addCategory(Intent.CATEGORY_OPENABLE);
        ((AppCompatActivity) context).startActivityForResult(i, requestCode);

    }

    /**
     * REPLACE FRAGMENT
     */
    public void Replace_Fragment(Fragment fragment, int id, Context context) {
        //ADD FRAGMENT TO ACTIVITY
        Fragment home = fragment;
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(id, home).addToBackStack(null).commit();
    }

    /**
     * convert to byte array
     */
    public Bitmap convertToBitmap(byte[] b) {
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * SET LANGUAGE
     */
    public static void set_language(String Lan, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale(Lan.toLowerCase()));
        resources.updateConfiguration(configuration, displayMetrics);

    }


    /**
     * SET PROGRESS DIALOG
     */

    public void set_dialog(Context context) {
        String loading = context.getResources().getString(R.string.loading_c);

        pd = new ProgressDialog(context);
        pd.setMessage(loading);
        pd.setMessage(loading);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    /**
     * SET PROGRESS DIALOG DISMISS
     */

    public void dismiss_dialog(Context context) {
        pd.dismiss();
    }


    /**
     * GET FIEL NAME
     */

    public String getFileName(Uri uri, Context context) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    /**
     * YOYO LIBRARY
     */
    public static void yoyo(int id, View v) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(1)
                .playOn(v.findViewById(id));
    }

    /**
     * @return TOKEN
     */
    static String token;

    public static String firebase_token() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
                Log.e("token_is", token);
                // send it to server
            }
        });
        return token;
    }

    /**
     * SET QUANTITY INCREASE AND DECREASE
     */
    int quantityNum = 0;
    int totalPrice = 0;

    public void setQuantity(TextView inc, TextView dec, int quantity, TextView quantityText, int tPrice,
                            int price, TextView totalPriceText, Context context,
                            TextView quantityText2, TextView totalPriceText2) {
        quantityNum = quantity;
        totalPrice = tPrice;
        //SET INCREASE
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityNum++;
                quantityText.setText("" + quantityNum); //SET QUANTITY PRICE

                //SET TOTAL PRICE
                totalPrice = quantityNum * price; //GET TOTAL PRICE
                totalPriceText.setText("" + totalPrice + " " + context.getString(R.string.egp)); //SET TOTAL PRICE

                //SET SECOND QUANTITY  CHECK IF NULL OR NOT
                if (quantityText2 != null) {
                    quantityText2.setText("" + quantityNum); //SET QUANTITY PRICE
                    totalPriceText2.setText("" + "" + totalPrice + " " + context.getString(R.string.egp)); //SET TOTAL PRICE

                }
            }
        });

        //SET DECREASE
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityNum == 1) {

                    Toasty.warning(context, context.getString(R.string.quantityCantBeLess), Toasty.LENGTH_SHORT).show();
                } else {
                    quantityNum--;
                    quantityText.setText("" + quantityNum); //SET QUANTITY PRICE

                    //SET TOTAL PRICE
                    totalPrice = quantityNum * price; //GET TOTAL PRICE
                    totalPriceText.setText("" + totalPrice + " " + context.getString(R.string.egp)); //SET TOTAL PRICE

                    //SET SECOND QUANTITY  CHECK IF NULL OR NOT
                    if (quantityText2 != null) {
                        quantityText2.setText("" + quantityNum); //SET QUANTITY PRICE
                        totalPriceText2.setText("" + "" + totalPrice + " " + context.getString(R.string.egp)); //SET TOTAL PRICE

                    }
                }
            }
        });
    }


    /**
     * GET PERMISSION IF YES OR NO
     */

    public boolean getLocationPermission(Context context, Activity activity) {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
            } else {
                ActivityCompat.requestPermissions(activity,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(activity,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
        return mLocationPermissionsGranted;
    }

    /**
     * @param uri
     * @param context
     * @return
     */
    public String getRealPathFromURI(Uri uri, Context context) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    /**
     * REDUCE FILE SIZE
     * @param file
     * @return
     */
    public File saveBitmapToFile(File file) {
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }
}
