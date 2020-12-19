package www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.SingleProduct;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class ProductDetailsModelView {
    androidx.lifecycle.MutableLiveData<SingleProduct> MutableLiveData = new MutableLiveData<>();
    void getData(String token,String ProductId)
    {
        Log.e("ttt", ProductId);
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<SingleProduct> StoreByService = NetworkInterface.SingleProduct(token, ProductId);

        StoreByService.enqueue(new Callback<SingleProduct>() {
            @Override
            public void onResponse(Call<SingleProduct> call, Response<SingleProduct> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SingleProduct> call, Throwable t) {

            }
        });
    }

}
