package www.gift_vouchers.marasel.MainScreen.ui.MyOrder.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.MyOrderList;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.MyOrderRoot;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.Product;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.Store;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.Time;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Pattern.MyOrderAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.MyOrderBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class myOrder extends Fragment {
    MyOrderBinding binding;
    MyOrderModelView myOrderModelView;
    Datum[] datum;
    Store store;
    Product[] products;
    ArrayList<MyOrderList> MyOrderList = new ArrayList<>();
    Time time;

    public myOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.my_order, container, false);
        View view = binding.getRoot();

        getData();

        return view;
    }


    void getData() {
        myOrderModelView = new MyOrderModelView();
        myOrderModelView.getData("Bearer " + new saved_data().get_token(getContext()));

        myOrderModelView.MutableLiveDataMakeOrder.observe(this, new Observer<MyOrderRoot>() {
            @Override
            public void onChanged(MyOrderRoot myOrderRoot) {
                datum = myOrderRoot.getData();
                for (int i = 0; i < datum.length; i++) {
                    store = datum[i].getStore();
                    products = datum[i].getProducts();
                    time = datum[i].getTime();

                    //ADD DATA TO LIST
                    MyOrderList.add(new MyOrderList("" + datum[i].getId(),
                            store.getName(), store.getCat(), "#" + datum[i].getId(),
                            time.getName(), "" + datum[i].getStatus(), store.getIcon()));
                }

                new utils_adapter().Adapter(binding.orderList, new MyOrderAdapter(getContext(), MyOrderList), getContext());
            }
        });
    }
}
