package www.gift_vouchers.marasel.AuthScreens.ui.SignUp.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.AuthScreens.Model.AuthRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;


public class SignUpModeView extends ViewModel {
    androidx.lifecycle.MutableLiveData<AuthRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String phone, String email, String password, String social, String userType) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AuthRoot> call = NetworkInterface.signUp(phone, email, password, social, userType, social);

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
