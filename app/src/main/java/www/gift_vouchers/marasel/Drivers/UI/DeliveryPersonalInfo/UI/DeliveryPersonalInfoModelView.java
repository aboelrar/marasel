package www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.DeliveryInfoRoot;
import www.gift_vouchers.marasel.Drivers.UI.DriverInfo.Model.DriverInfoRoot;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.Model.ActiveDriverRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class DeliveryPersonalInfoModelView {
    androidx.lifecycle.MutableLiveData<DeliveryInfoRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<DeliveryInfoRoot> activeDriverRoot = NetworkInterface.myInfo(token);

        activeDriverRoot.enqueue(new Callback<DeliveryInfoRoot>() {
            @Override
            public void onResponse(Call<DeliveryInfoRoot> call, Response<DeliveryInfoRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DeliveryInfoRoot> call, Throwable t) {

            }
        });
    }

}
