package www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.AvailableOrderRoot;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.Datum;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.OrderList;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.Store;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model.Time;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Pattern.AvailableOrderAdapter;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.UI.DeliveryPersonalInfoModelView;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.AvailableOrdersBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AvailableOrders extends Fragment {
    AvailableOrdersBinding binding;
    AvailableOrdersModelView availableOrdersModelView;
    Datum[] datum;
    Store store;
    Time time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.available_orders, container, false);
        View view = binding.getRoot();

        availableOrdersModelView = new AvailableOrdersModelView();

        getData();

        return view;
    }


    void getData() {
        availableOrdersModelView.getData("Bearer " + new saved_data().get_token(getContext()));
        availableOrdersModelView.MutableLiveData.observe(this, new Observer<AvailableOrderRoot>() {
            @Override
            public void onChanged(AvailableOrderRoot availableOrderRoot) {
                ArrayList<OrderList> OrderList = new ArrayList<>();
                datum = availableOrderRoot.getData();

                for (int i = 0; i < datum.length; i++) {
                    store = datum[i].getStore();
                    time = datum[i].getTime();

                    OrderList.add(new OrderList("" + datum[i].getId(), store.getCat(), store.getName(),
                            "#" + datum[i].getId(), time.getName(), store.getIcon()));
                }

                new utils_adapter().Adapter(binding.orderList, new AvailableOrderAdapter(getContext(), OrderList), getContext());
            }
        });
    }
}