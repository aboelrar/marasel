package www.gift_vouchers.marasel.Drivers.UI.MyOffers.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.Client;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.Datum;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.Driver;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.MyOfferList;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.MyOffersRoot;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.Order;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.Product;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.Time;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Pattern.MyOfferAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.MyOffersBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 * * create an instance of this fragment.
 */
public class MyOffers extends Fragment {
    MyOffersBinding binding;
    MyOffersViewModel myOffersViewModel;
    Datum[] data;
    Product[] products;
    Order order;
    Time time;
    Client client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.my_offers, container, false);
        View view = binding.getRoot();

        myOffersViewModel = new MyOffersViewModel();

        getData();

        return view;
    }

    void getData() {
        myOffersViewModel.getData("Bearer " + new saved_data().get_token(getContext()));
        myOffersViewModel.MutableLiveData.observe(this, new Observer<MyOffersRoot>() {
            @Override
            public void onChanged(MyOffersRoot myOffersRoot) {
                ArrayList<MyOfferList> myOfferLists = new ArrayList<>();

                data = myOffersRoot.getData();
                Log.e("products", "" + data.length);

                for (int index = 0; index < data.length; index++) {
                    order = data[index].getOrder();
                    products = order.getProducts();
                    client = order.getClient();

                    Log.e("products", "" + products.length);
                    time = order.getTime();

                    for (int i = 0; i < products.length; i++) {
                        myOfferLists.add(new MyOfferList("" + order.getId(), products[i].getQuantity() + " " + products[i].getCat(),
                                products[i].getIcon(), order.getAddress(), time.getName(), data[index].getStatus(), client.getImage()));
                    }
                }
                new utils_adapter().Adapter(binding.offerList, new MyOfferAdapter(getContext(), myOfferLists), getContext());
            }
        });
    }

}