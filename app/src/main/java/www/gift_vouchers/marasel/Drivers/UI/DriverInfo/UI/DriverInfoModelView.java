package www.gift_vouchers.marasel.Drivers.UI.DriverInfo.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.Drivers.UI.DriverInfo.Model.DriverInfoRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class DriverInfoModelView {
    androidx.lifecycle.MutableLiveData<DriverInfoRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token, String bankNum, String bank_type, File car_back_image_f,
                 File car_front_image_f, File license_image_f, File id_image_f, String name,
                 String lat, String lng) {

        //SET CAR BACK IMAGE
        MultipartBody.Part carBackImageBody = null;
        if (car_back_image_f != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), car_back_image_f);
            carBackImageBody = MultipartBody.Part.createFormData("car_back_image", car_back_image_f.getName(), requestFile);
        }

        //SET CAR FRONT IMAGE
        MultipartBody.Part carFrontImageBody = null;
        if (car_front_image_f != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), car_front_image_f);
            carFrontImageBody = MultipartBody.Part.createFormData("car_front_image", car_front_image_f.getName(), requestFile);
        }

        //SET LICENSE IMAGE
        MultipartBody.Part licenseImageBody = null;
        if (license_image_f != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), license_image_f);
            licenseImageBody = MultipartBody.Part.createFormData("license_image", license_image_f.getName(), requestFile);
        }

        //SET ID IMAGE
        MultipartBody.Part idImageBody = null;
        if (id_image_f != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), id_image_f);
            idImageBody = MultipartBody.Part.createFormData("license_image", id_image_f.getName(), requestFile);
        }

        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<DriverInfoRoot> activeDriverRoot = NetworkInterface.driverInfo(token, bankNum, bank_type, carBackImageBody
                , carFrontImageBody, licenseImageBody, idImageBody, name, lat, lng);

        activeDriverRoot.enqueue(new Callback<DriverInfoRoot>() {
            @Override
            public void onResponse(Call<DriverInfoRoot> call, Response<DriverInfoRoot> response) {
                MutableLiveData.setValue(response.body());
                Log.e("tito",""+""+response.message());

            }

            @Override
            public void onFailure(Call<DriverInfoRoot> call, Throwable t) {
                Log.e("tito",""+t.getLocalizedMessage());
            }
        });
    }

}
