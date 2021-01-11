package www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.AvailableOrderRoot;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.DeliveryInfoRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class AvailableOrdersModelView {
    androidx.lifecycle.MutableLiveData<AvailableOrderRoot> MutableLiveData = new MutableLiveData<>();
    void getData(String token) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AvailableOrderRoot> availableOrders = NetworkInterface.availableOrders(token);

        availableOrders.enqueue(new Callback<AvailableOrderRoot>() {
            @Override
            public void onResponse(Call<AvailableOrderRoot> call, Response<AvailableOrderRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AvailableOrderRoot> call, Throwable t) {

            }
        });
    }

}
