package www.gift_vouchers.marasel.MainScreen.ui.Product.Ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.Product.Model.ProductsByCat;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class ProductModelView {
    androidx.lifecycle.MutableLiveData<ProductsByCat> MutableLiveData = new MutableLiveData<>();
    void getData(String token,String storeId,String CatId)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<ProductsByCat> StoreByService = NetworkInterface.ProductsByCat(token,storeId,CatId);

        StoreByService.enqueue(new Callback<ProductsByCat>() {
            @Override
            public void onResponse(Call<ProductsByCat> call, Response<ProductsByCat> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductsByCat> call, Throwable t) {
                Log.e("eeee",""+ t.getLocalizedMessage());
            }
        });
    }

}
