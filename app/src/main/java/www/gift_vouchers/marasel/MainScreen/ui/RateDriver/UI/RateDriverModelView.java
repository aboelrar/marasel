package www.gift_vouchers.marasel.MainScreen.ui.RateDriver.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.DeliveryInfoRoot;
import www.gift_vouchers.marasel.MainScreen.ui.RateDriver.Model.RateDriverRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class RateDriverModelView {
    androidx.lifecycle.MutableLiveData<RateDriverRoot> MutableLiveData = new MutableLiveData<>();


    void getData(String token, String comment, int rate, String id) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<RateDriverRoot> rateStore = NetworkInterface.rateDriver(token, id, comment, rate);

        rateStore.enqueue(new Callback<RateDriverRoot>() {
            @Override
            public void onResponse(Call<RateDriverRoot> call, Response<RateDriverRoot> response) {
                Log.e("msg_is", "" + response.message());
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RateDriverRoot> call, Throwable t) {
                Log.e("eeee", "" + t.getLocalizedMessage());
            }
        });
    }


}
