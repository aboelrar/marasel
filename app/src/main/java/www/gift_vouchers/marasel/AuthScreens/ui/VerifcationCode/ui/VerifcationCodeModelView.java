package www.gift_vouchers.marasel.AuthScreens.ui.VerifcationCode.ui;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.AvailableOrderRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class VerifcationCodeModelView {
    androidx.lifecycle.MutableLiveData<AvailableOrderRoot> MutableLiveDataSingleOrder = new MutableLiveData<>();

    void getData(String token,String code)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AvailableOrderRoot> sendCode = NetworkInterface.activeUser(token, code);

        sendCode.enqueue(new Callback<AvailableOrderRoot>() {
            @Override
            public void onResponse(Call<AvailableOrderRoot> call, Response<AvailableOrderRoot> response) {
                MutableLiveDataSingleOrder.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AvailableOrderRoot> call, Throwable t) {

            }
        });
    }

    void getDataResend(String token)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AvailableOrderRoot> resendCode = NetworkInterface.resendCode(token);

        resendCode.enqueue(new Callback<AvailableOrderRoot>() {
            @Override
            public void onResponse(Call<AvailableOrderRoot> call, Response<AvailableOrderRoot> response) {
                MutableLiveDataSingleOrder.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AvailableOrderRoot> call, Throwable t) {

            }
        });
    }
}
