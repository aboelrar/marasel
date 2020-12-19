package www.gift_vouchers.marasel.MainScreen.ui.Store.ui;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.SingleStore;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class StoreModelView {
    androidx.lifecycle.MutableLiveData<SingleStore> MutableLiveData = new MutableLiveData<>();
    void getData(String token,String storeId)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<SingleStore> StoreByService = NetworkInterface.SingleStore(token,storeId);

       StoreByService.enqueue(new Callback<SingleStore>() {
           @Override
           public void onResponse(Call<SingleStore> call, Response<SingleStore> response) {
               MutableLiveData.setValue(response.body());
           }

           @Override
           public void onFailure(Call<SingleStore> call, Throwable t) {

           }
       });
    }
}
