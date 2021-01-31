package www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.Model.MyDiscountCodeRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class MyDiscountCopounsModelView {
    androidx.lifecycle.MutableLiveData<MyDiscountCodeRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<MyDiscountCodeRoot> StoreByService = NetworkInterface.myDiscountCodes(token);

        StoreByService.enqueue(new Callback<MyDiscountCodeRoot>() {
            @Override
            public void onResponse(Call<MyDiscountCodeRoot> call, Response<MyDiscountCodeRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MyDiscountCodeRoot> call, Throwable t) {

            }
        });
    }

}
