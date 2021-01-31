package www.gift_vouchers.marasel.MainScreen.ui.AddCopounCode.UI;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.AddCopounCode.Model.AddCouponCodeRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class AddCopounCodeModelView {
    androidx.lifecycle.MutableLiveData<AddCouponCodeRoot> MutableLiveData = new MutableLiveData<>();

    void getData(String token, String code) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AddCouponCodeRoot> addCouponCodeRoot = NetworkInterface.addCouponCode(token, code);

        addCouponCodeRoot.enqueue(new Callback<AddCouponCodeRoot>() {
            @Override
            public void onResponse(Call<AddCouponCodeRoot> call, Response<AddCouponCodeRoot> response) {
                MutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AddCouponCodeRoot> call, Throwable t) {

            }
        });

    }
}
