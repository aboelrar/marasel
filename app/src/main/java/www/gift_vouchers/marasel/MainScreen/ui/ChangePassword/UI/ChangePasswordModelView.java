package www.gift_vouchers.marasel.MainScreen.ui.ChangePassword.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.ChangePassword.Model.ChangePasswordRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class ChangePasswordModelView {

    androidx.lifecycle.MutableLiveData<ChangePasswordRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token, String newPass, String oldPass) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<ChangePasswordRoot> addCouponCodeRoot = NetworkInterface.changePassword(token, newPass, oldPass);

        addCouponCodeRoot.enqueue(new Callback<ChangePasswordRoot>() {
            @Override
            public void onResponse(Call<ChangePasswordRoot> call, Response<ChangePasswordRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ChangePasswordRoot> call, Throwable t) {

            }
        });

    }
}
