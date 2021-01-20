package www.gift_vouchers.marasel.MainScreen.ui.Offers.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.MyOrdersRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class OffersModelView {
    MutableLiveData<MyOrdersRoot> MutableLiveOffers = new MutableLiveData<>();

    void getData(String token, String id) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<MyOrdersRoot> MyOrdersRoot = NetworkInterface.singleOrder(token,id);

        MyOrdersRoot.enqueue(new Callback<MyOrdersRoot>() {
            @Override
            public void onResponse(Call<MyOrdersRoot> call, Response<MyOrdersRoot> response) {
                MutableLiveOffers.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MyOrdersRoot> call, Throwable t) {
                Log.e("eeee",""+ t.getLocalizedMessage());
            }
        });
    }

    void acceptOrRejectOrder()
    {

    }
}
