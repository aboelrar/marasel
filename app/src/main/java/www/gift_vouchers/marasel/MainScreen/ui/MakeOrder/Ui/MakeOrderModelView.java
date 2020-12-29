package www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Ui;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.DeliveryPlace;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class MakeOrderModelView {
    androidx.lifecycle.MutableLiveData<DeliveryPlace> MutableLiveData = new MutableLiveData<>();
    void getData(String token)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<DeliveryPlace> StoreByService = NetworkInterface.orderTimes(token);

        StoreByService.enqueue(new Callback<DeliveryPlace>() {
            @Override
            public void onResponse(Call<DeliveryPlace> call, Response<DeliveryPlace> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DeliveryPlace> call, Throwable t) {

            }
        });

    }
}
