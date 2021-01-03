package www.gift_vouchers.marasel.MainScreen.ui.RateStore.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.RateStore.Model.RateStoreRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class RateStoreModelView {
    androidx.lifecycle.MutableLiveData<RateStoreRoot> MutableLiveData = new MutableLiveData<>();
    void getData(String token,String storeId,int rate)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<RateStoreRoot> rateStore = NetworkInterface.rateStore(token,storeId,rate);

        rateStore.enqueue(new Callback<RateStoreRoot>() {
            @Override
            public void onResponse(Call<RateStoreRoot> call, Response<RateStoreRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RateStoreRoot> call, Throwable t) {
                Log.e("eeee",""+ t.getLocalizedMessage());
            }
        });
    }
}
