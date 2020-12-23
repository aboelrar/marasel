package www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.AddToCartRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class AddToCartModelView {
    androidx.lifecycle.MutableLiveData<AddToCartRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token, String productId, String quantity) {

        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AddToCartRoot> addToCartRoot = NetworkInterface.addToCart(token, productId, quantity);

        addToCartRoot.enqueue(new Callback<www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.AddToCartRoot>() {
            @Override
            public void onResponse(Call<www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.AddToCartRoot> call, Response<www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.AddToCartRoot> response) {
                Log.e("result", ""+response.code());

                MutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.AddToCartRoot> call, Throwable t) {

            }
        });
    }
}
