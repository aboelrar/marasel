package www.gift_vouchers.marasel.Drivers.UI.MyOffers.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.MyOffersRoot;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.Model.ActiveDriverRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class MyOffersViewModel {
    androidx.lifecycle.MutableLiveData<MyOffersRoot> MutableLiveData = new MutableLiveData<>();
    void getData(String token)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<MyOffersRoot> activeDriverRoot = NetworkInterface.myOffer(token);

        activeDriverRoot.enqueue(new Callback<MyOffersRoot>() {
            @Override
            public void onResponse(Call<MyOffersRoot> call, Response<MyOffersRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MyOffersRoot> call, Throwable t) {

            }
        });
    }
}
