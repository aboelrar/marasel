package www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.Model.ActiveDriverRoot;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class WorkAsStarModelView {
    androidx.lifecycle.MutableLiveData<ActiveDriverRoot> MutableLiveData = new MutableLiveData<>();
    void getData(String token)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<ActiveDriverRoot> activeDriverRoot = NetworkInterface.activeDriver(token);

        activeDriverRoot.enqueue(new Callback<ActiveDriverRoot>() {
            @Override
            public void onResponse(Call<ActiveDriverRoot> call, Response<ActiveDriverRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ActiveDriverRoot> call, Throwable t) {

            }
        });
    }

}
