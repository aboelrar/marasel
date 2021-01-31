package www.gift_vouchers.marasel.MainScreen.ui.Search.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.SearchRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class SearchModelView {
    androidx.lifecycle.MutableLiveData<SearchRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token, String lat, String lng, String search) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<SearchRoot> searchStore = NetworkInterface.search(token, lat, lng, search);

        searchStore.enqueue(new Callback<SearchRoot>() {
            @Override
            public void onResponse(Call<SearchRoot> call, Response<SearchRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SearchRoot> call, Throwable t) {

            }
        });

    }
}
