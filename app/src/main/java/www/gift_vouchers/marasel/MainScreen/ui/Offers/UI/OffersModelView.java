package www.gift_vouchers.marasel.MainScreen.ui.Offers.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.AcceptOrRejectModel.AcceptedOrRejectedOfferRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.CancelOrderRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.MyOrdersRoot;
import www.gift_vouchers.marasel.NetworkLayer.APIClient;

public class OffersModelView {
    MutableLiveData<MyOrdersRoot> MutableLiveOffers = new MutableLiveData<>();
    MutableLiveData<CancelOrderRoot> MutableLiveCancelOrder = new MutableLiveData<>();
    MutableLiveData<AcceptedOrRejectedOfferRoot> MutableLiveAcceptOrRejectOrder = new MutableLiveData<>();


    void getData(String token, String id) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<MyOrdersRoot> MyOrdersRoot = NetworkInterface.singleOrder(token, id);

        MyOrdersRoot.enqueue(new Callback<MyOrdersRoot>() {
            @Override
            public void onResponse(Call<MyOrdersRoot> call, Response<MyOrdersRoot> response) {
                MutableLiveOffers.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MyOrdersRoot> call, Throwable t) {
                Log.e("eeee", "" + t.getLocalizedMessage());
            }
        });
    }

    void cancelOrder(String token, String id, String type, String reason) {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<CancelOrderRoot> CancelOrderRoot = NetworkInterface.cancelOrder(token, id, type, reason);

        CancelOrderRoot.enqueue(new Callback<CancelOrderRoot>() {
            @Override
            public void onResponse(Call<CancelOrderRoot> call, Response<CancelOrderRoot> response) {
                Log.e("toto_kapoto", "" + response.code());
                MutableLiveCancelOrder.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CancelOrderRoot> call, Throwable t) {
                Log.e("eeee", "" + t.getLocalizedMessage());
            }
        });
    }

   public void AcceptOrReject(String token, String orderId, String offerId, String status)
    {
        www.gift_vouchers.marasel.NetworkLayer.NetworkInterface NetworkInterface = APIClient.getClient().create(www.gift_vouchers.marasel.NetworkLayer.NetworkInterface.class);
        Call<AcceptedOrRejectedOfferRoot> CancelOrderRoot = NetworkInterface.acceptedOrRejectedOffer(token, orderId, offerId, status);

        CancelOrderRoot.enqueue(new Callback<AcceptedOrRejectedOfferRoot>() {
            @Override
            public void onResponse(Call<AcceptedOrRejectedOfferRoot> call, Response<AcceptedOrRejectedOfferRoot> response) {
                Log.e("toto_kapoto", "" + response.code());
                MutableLiveAcceptOrRejectOrder.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AcceptedOrRejectedOfferRoot> call, Throwable t) {
                Log.e("eeee", "" + t.getLocalizedMessage());
            }
        });
    }
}
