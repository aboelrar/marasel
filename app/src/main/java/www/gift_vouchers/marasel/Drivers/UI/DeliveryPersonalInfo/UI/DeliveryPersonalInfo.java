package www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;

import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.UI.AvailableOrders;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.Datum;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.Delivery;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.DeliveryInfoRoot;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.UI.WorkAsStarModelView;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.DeliveryPersonalInfoBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.local_data.send_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryPersonalInfo extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    DeliveryPersonalInfoBinding binding;
    DeliveryPersonalInfoModelView deliveryPersonalInfoModelView;
    Datum datum;
    Delivery delivery;

    public DeliveryPersonalInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.delivery_personal_info, container, false);
        View view = binding.getRoot();

        deliveryPersonalInfoModelView = new DeliveryPersonalInfoModelView();

        binding.switcher.setOnCheckedChangeListener(this);

        binding.goAvailableOrder.setOnClickListener(this);

        getData();

        return view;
    }

    void getData() {
        deliveryPersonalInfoModelView.getData("Bearer " + new saved_data().get_token(getContext()));
        deliveryPersonalInfoModelView.MutableLiveData.observe(this, new Observer<DeliveryInfoRoot>() {
            @Override
            public void onChanged(DeliveryInfoRoot deliveryInfoRoot) {
                datum = deliveryInfoRoot.getData();
                delivery = datum.getDelivery();

                Glide.with(getContext()).load(datum.getImage()).into(binding.img); //SET IMAGE

                //SET BALANCE
                if (datum.getBalance().equals("")) {
                    binding.balance.setText(getString(R.string.zero_balance));
                } else {
                    binding.balance.setText(datum.getBalance() + " " + getString(R.string.egp));
                }

                binding.personalName.setText(delivery.getName()); //SET NAME

                binding.star.setText("" + delivery.getRate()); //GET RATE

                binding.ordersCount.setText("" + delivery.getOrdersCount()); //COUNT OF ORDERS

                binding.rateCounts.setText("" + delivery.getCountOfRate()); //RATE COUNT

                binding.totalAvailableOrders.setText("" + delivery.getAvailableOrdersCount()); //GET AVAILABLE ORDER COUNTS

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        send_data.checkActiveStar(getContext(), false); //SET CHECK FALSE
        getActivity().onBackPressed(); //BACK TO PREVIOUS FRAGMENT
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goAvailableOrder) {
            new utils().Replace_Fragment(new AvailableOrders(), R.id.frag, getContext());
        }
    }
}
