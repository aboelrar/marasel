package www.gift_vouchers.marasel.MainScreen.ui.MyOrder.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.MakeOrder;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.MyOrderRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Product.Model.ProductsByCat;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class MyOrderModelView {
    androidx.lifecycle.MutableLiveData<MyOrderRoot> MutableLiveDataMakeOrder = new MutableLiveData<>();

    void getData(String token) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<MyOrderRoot> StoreByService = NetworkInterface.myOrders(token);

        StoreByService.enqueue(new Callback<MyOrderRoot>() {
            @Override
            public void onResponse(Call<MyOrderRoot> call, Response<MyOrderRoot> response) {
                MutableLiveDataMakeOrder.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MyOrderRoot> call, Throwable t) {
                Log.e("eeee", "" + t.getLocalizedMessage());
            }
        });
    }
}
