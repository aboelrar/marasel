package www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.Model.editProfileRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class PersonalInformationModelView {
    androidx.lifecycle.MutableLiveData<editProfileRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token, String name, String phone, String email, File file, String gender) {

        //SET CAR BACK IMAGE
        MultipartBody.Part imageBody = null;
        if (file != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            imageBody = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        }

        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<editProfileRoot> editProfileRoot = NetworkInterface.editProfile(token, name, phone, email, imageBody, gender);

        editProfileRoot.enqueue(new Callback<editProfileRoot>() {
            @Override
            public void onResponse(Call<editProfileRoot> call, Response<editProfileRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<editProfileRoot> call, Throwable t) {
                Log.e("eeee", "" + t.getLocalizedMessage());
            }
        });
    }

}
