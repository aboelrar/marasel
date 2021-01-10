package www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.Datum;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.Delivery;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.DeliveryInfoRoot;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.UI.WorkAsStarModelView;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.DeliveryPersonalInfoBinding;
import www.gift_vouchers.marasel.local_data.saved_data;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryPersonalInfo extends Fragment {
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

            }
        });
    }
}
