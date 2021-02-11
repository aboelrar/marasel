package www.gift_vouchers.marasel.AuthScreens.ui.login.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.AuthScreens.Model.AuthRoot;
import www.gift_vouchers.marasel.AuthScreens.Model.Datum;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;


public class LoginModeView extends ViewModel {
    androidx.lifecycle.MutableLiveData<AuthRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String emailOrPhone, String password, String social) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AuthRoot> call = NetworkInterface.login(emailOrPhone, password, social, social);

        call.enqueue(new Callback<AuthRoot>() {
            @Override
            public void onResponse(Call<AuthRoot> call, Response<AuthRoot> response) {
                Log.e("ooopos", "" + response.body().getMessage());
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AuthRoot> call, Throwable t) {
                Log.e("ooopos", "" + t.getMessage());
            }
        });
    }
}
