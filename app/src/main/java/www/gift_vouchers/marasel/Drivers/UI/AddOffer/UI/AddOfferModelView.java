package www.gift_vouchers.marasel.Drivers.UI.AddOffer.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.AddOfferRoot;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.SingleOrderRoot;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.AvailableOrderRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;
import www.gift_vouchers.marasel.NetworkLayer.NetworkInterface;

public class AddOfferModelView {
    androidx.lifecycle.MutableLiveData<AddOfferRoot> MutableLiveData = new MutableLiveData<>();
    androidx.lifecycle.MutableLiveData<SingleOrderRoot> MutableLiveDataSingleOrder = new MutableLiveData<>();

    void getData(String token,String orderId)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<SingleOrderRoot> singleOrder = NetworkInterface.SingleOrder(token, orderId);

        singleOrder.enqueue(new Callback<SingleOrderRoot>() {
            @Override
            public void onResponse(Call<SingleOrderRoot> call, Response<SingleOrderRoot> response) {
                MutableLiveDataSingleOrder.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SingleOrderRoot> call, Throwable t) {

            }
        });
    }

    void setData(String token, String id, String price, String time, String timeType, String note) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AddOfferRoot> availableOrders = NetworkInterface.addOffer(token, id, price, time, timeType, note);

        availableOrders.enqueue(new Callback<AddOfferRoot>() {
            @Override
            public void onResponse(Call<AddOfferRoot> call, Response<AddOfferRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AddOfferRoot> call, Throwable t) {

            }
        });
    }

}
