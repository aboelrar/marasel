package www.gift_vouchers.marasel.MainScreen.ui.Cart.Ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.CartRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.DeleteProductRoot;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class CartModelView {
    androidx.lifecycle.MutableLiveData<CartRoot> MutableLiveData = new MutableLiveData<>();
    public androidx.lifecycle.MutableLiveData<DeleteProductRoot> MutableLiveDataDelete = new MutableLiveData<>();

    void getData(String token) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<CartRoot> cartRoot = NetworkInterface.myCart(token);

        cartRoot.enqueue(new Callback<CartRoot>() {
            @Override
            public void onResponse(Call<CartRoot> call, Response<CartRoot> response) {
                Log.e("polika", "" + response.code());

                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CartRoot> call, Throwable t) {

            }
        });
    }

    public void deleteData(String token, String id) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<DeleteProductRoot> deleteCartRoot = NetworkInterface.deleteCart(token, id);

        deleteCartRoot.enqueue(new Callback<DeleteProductRoot>() {
            @Override
            public void onResponse(Call<DeleteProductRoot> call, Response<DeleteProductRoot> response) {
                MutableLiveDataDelete.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DeleteProductRoot> call, Throwable t) {

            }
        });
    }
}
