package www.gift_vouchers.marasel.chat.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;
import www.gift_vouchers.marasel.chat.Model.UploadImage;

public class ChatModelView {
    androidx.lifecycle.MutableLiveData<UploadImage> MutableLiveData = new MutableLiveData<>();

    void getData(File file) {

        //SET CAR BACK IMAGE
        MultipartBody.Part imageBody = null;
        if (file != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            imageBody = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        }

        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<UploadImage> uploadImage = NetworkInterface.saveImage(imageBody);

        uploadImage.enqueue(new Callback<UploadImage>() {
            @Override
            public void onResponse(Call<UploadImage> call, Response<UploadImage> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UploadImage> call, Throwable t) {
                Log.e("eeee", "" + t.getLocalizedMessage());
            }
        });
    }
}
