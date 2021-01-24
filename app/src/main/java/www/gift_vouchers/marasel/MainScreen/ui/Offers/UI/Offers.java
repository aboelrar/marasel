package www.gift_vouchers.marasel.MainScreen.ui.Offers.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.AcceptOrRejectModel.AcceptedOrRejectedOfferRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Delivery;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Driver;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.MyOrdersRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Offer;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.OfferList;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Order;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Pattern.OffersAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.OffersBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Offers extends Fragment implements View.OnClickListener, Callback {
    OffersBinding binding;
    ArrayList<OfferList> offerList = new ArrayList<>();
    Datum datum;
    Offer[] offers;
    Driver driver;
    Delivery delivery;
    Callback callback;
    String type;
    OffersModelView offersModelView;
    String orderId;
    Order order;

    public Offers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.offers, container, false);
        View view = binding.getRoot();

        orderId = getArguments().getString("id");

        callback = this;

        offersModelView = new OffersModelView();

        getData();

        binding.cancelOrder.setOnClickListener(this);

        return view;
    }

    void getData() {
        offersModelView.getData("Bearer " + new saved_data().get_token(getContext()), orderId);

        offersModelView.MutableLiveOffers.observe(this, new Observer<MyOrdersRoot>() {
            @Override
            public void onChanged(MyOrdersRoot myOrdersRoot) {
                datum = myOrdersRoot.getData();
                offers = datum.getOffers();
                order = datum.getOrder();

                for (int index = 0; index < offers.length; index++) {
                    driver = offers[index].getDriver();
                    delivery = driver.getDelivery();
                    offerList.add(new OfferList("" + offers[index].getId(), driver.getName(),
                            "" + delivery.getRate(), "0", offers[index].getNote(), driver.getImage()
                    ));
                }

                new utils_adapter().Adapter(binding.offerList, new OffersAdapter(getContext(), offerList, offersModelView,
                        "" + order.getId()), getContext());

            }
        });

        //OBSERVE ACCEPT OR REJECT OFFER
        offersModelView.MutableLiveAcceptOrRejectOrder.observe(this, new Observer<AcceptedOrRejectedOfferRoot>() {
            @Override
            public void onChanged(AcceptedOrRejectedOfferRoot acceptedOrRejectedOfferRoot) {
                new utils().dismiss_dialog(getContext());
                Toasty.success(getContext(), acceptedOrRejectedOfferRoot.getMessage(), Toasty.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_order) {
            new CancelDialog().dialog(getContext(), R.layout.cancel_order, .90, callback, offersModelView, orderId);
        }
    }

    @Override
    public void setType(String type) {
        this.type = type;
        Toast.makeText(getContext(), type, Toast.LENGTH_LONG).show();
    }
}