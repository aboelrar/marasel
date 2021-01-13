package www.gift_vouchers.marasel.Drivers.UI.AddOffer.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.AddOfferRoot;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.Order;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.OrdersList;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.Product;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.SingleOrderData;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.SingleOrderRoot;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.Store;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model.Time;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.Patten.OrdersAdapter;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.UI.AvailableOrdersModelView;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.AddOfferBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils_adapter;

import static www.gift_vouchers.marasel.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddOffer extends Fragment implements View.OnClickListener {
    AddOfferBinding binding;
    AddOfferModelView addOfferModelView;
    SingleOrderData singleOrderData;
    Order order;
    Store store;
    Time time;
    Product[] product;
    ArrayList<OrdersList> ordersLists = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.add_offer, container, false);
        View view = binding.getRoot();

        addOfferModelView = new AddOfferModelView();

        binding.submitOffer.setOnClickListener(this);

        getData();

        return view;
    }

    void setData() {
        addOfferModelView.setData("Bearer " + new saved_data().get_token(getContext()), getArguments().getString("id"),
                binding.price.getText().toString(), binding.time.getText().toString(), getString(R.string.hour), binding.writeNotes.getText().toString());

        addOfferModelView.MutableLiveData.observe(this, new Observer<AddOfferRoot>() {
            @Override
            public void onChanged(AddOfferRoot addOfferRoot) {
                Toasty.success(getContext(), "" + addOfferRoot.getMessage(), Toasty.LENGTH_LONG).show();
            }
        });
    }


    void getData() {
        addOfferModelView.getData("Bearer " + new saved_data().get_token(getContext()), getArguments().getString("id"));
        addOfferModelView.MutableLiveDataSingleOrder.observe(this, new Observer<SingleOrderRoot>() {
            @Override
            public void onChanged(SingleOrderRoot singleOrderRoot) {
                singleOrderData = singleOrderRoot.getData();
                order = singleOrderData.getOrder();
                store = order.getStore();
                time = order.getTime();
                product = order.getProducts();

                binding.orderNum.setText(getString(R.string.order_number) + " #" + order.getId());
                Glide.with(getContext()).load(store.getIcon()).into(binding.brandImg);
                binding.deliveryTime.setText(time.getName());
                binding.location.setText(order.getAddress());

                for (int i = 0; i < product.length; i++) {
                    ordersLists.add(new OrdersList("" + product[i].getId(), product[i].getName()));
                }
                new utils_adapter().Adapter(binding.orderList, new OrdersAdapter(getContext(), ordersLists), getContext());


            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_offer) {
            if (binding.time.getText().toString().equals("")) {
                String deliveryTimeVal = getResources().getString(R.string.pls_insert_delivery_time);
                binding.time.setError(deliveryTimeVal);
                yoyo(R.id.time, binding.time);
            } else if (binding.price.getText().toString().equals("")) {
                String deliveryPriceVal = getResources().getString(R.string.pls_insert_delivery_price);
                binding.price.setError(deliveryPriceVal);
                yoyo(R.id.price, binding.price);
            } else if (binding.writeNotes.getText().toString().equals("")) {
                String writeNotesVal = getResources().getString(R.string.write_your_notes);
                binding.writeNotes.setError(writeNotesVal);
                yoyo(R.id.write_notes, binding.writeNotes);
            } else {
                setData();
            }
        }
    }
}