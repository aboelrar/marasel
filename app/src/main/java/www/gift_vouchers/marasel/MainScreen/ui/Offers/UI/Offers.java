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

import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Delivery;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Driver;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.MyOrdersRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Offer;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.OfferList;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Pattern.OffersAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.OffersBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
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

        callback = this;

        getData();

        binding.cancelOrder.setOnClickListener(this);

        return view;
    }

    void getData() {
        OffersModelView offersModelView = new OffersModelView();
        offersModelView.getData("Bearer " + new saved_data().get_token(getContext()), getArguments().getString("id"));

        offersModelView.MutableLiveOffers.observe(this, new Observer<MyOrdersRoot>() {
            @Override
            public void onChanged(MyOrdersRoot myOrdersRoot) {
                datum = myOrdersRoot.getData();
                offers = datum.getOffers();

                for (int index = 0; index < offers.length; index++) {
                    driver = offers[index].getDriver();
                    delivery = driver.getDelivery();
                    offerList.add(new OfferList("" + offers[index].getId(), driver.getName(),
                            "" + delivery.getRate(), "0", offers[index].getNote(), driver.getImage()
                    ));
                }

                new utils_adapter().Adapter(binding.offerList, new OffersAdapter(getContext(), offerList), getContext());

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_order) {
            new CancelDialog().dialog(getContext(), R.layout.cancel_order, .90, callback);
        }
    }

    @Override
    public void setType(String type) {
        this.type = type;
        Toast.makeText(getContext(), type, Toast.LENGTH_LONG).show();
    }
}