package www.gift_vouchers.marasel.MainScreen.ui.Categories.ui;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class CategoriesModeView {
    androidx.lifecycle.MutableLiveData<StoreByService> MutableLiveData = new MutableLiveData<>();
    void getData(String token, String id, String page)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<StoreByService> StoreByService = NetworkInterface.StoreByService(token,id,page);

        StoreByService.enqueue(new Callback<www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService>() {
            @Override
            public void onResponse(Call<www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService> call, Response<www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService> call, Throwable t) {

            }
        });

    }
}
