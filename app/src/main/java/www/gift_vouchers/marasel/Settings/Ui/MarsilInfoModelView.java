package www.gift_vouchers.marasel.Settings.Ui;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;
import www.gift_vouchers.marasel.Settings.Model.AppInfoRoot;

public class MarsilInfoModelView {
   public androidx.lifecycle.MutableLiveData<AppInfoRoot> MutableLiveData = new MutableLiveData<>();

    public void getData(String token) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AppInfoRoot> appInfoRoot = NetworkInterface.maraselInfo(token);

        appInfoRoot.enqueue(new Callback<AppInfoRoot>() {
            @Override
            public void onResponse(Call<AppInfoRoot> call, Response<AppInfoRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AppInfoRoot> call, Throwable t) {

            }
        });

    }
}
