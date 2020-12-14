package www.gift_vouchers.marasel.MainScreen.ui.home.ui;

import androidx.lifecycle.MutableLiveData;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;
import www.gift_vouchers.marasel.local_data.saved_data;

public class HomeModeView {
   androidx.lifecycle.MutableLiveData<homeRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<homeRoot> homeRoot = NetworkInterface.home(token);

        homeRoot.enqueue(new Callback<www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot>() {
            @Override
            public void onResponse(Call<www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot> call, Response<www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot> call, Throwable t) {

            }
        });
    }
}
