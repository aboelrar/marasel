package www.gift_vouchers.marasel.MainScreen.ui.RateDriver.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.Datum;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.DeliveryInfoRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Delivery;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Driver;
import www.gift_vouchers.marasel.MainScreen.ui.RateDriver.Model.RateDriverRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.RateDriverBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RateDriver extends Fragment implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    RateDriverBinding binding;
    RateDriverModelView rateDriverModelView;
    int rating = 15;
    Datum datum;
    Delivery delivery;

    public RateDriver(Delivery delivery) {
        // Required empty public constructor
        this.delivery = delivery;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.rate_driver, container, false);
        View view = binding.getRoot();

        rateDriverModelView = new RateDriverModelView();

        getDriverData();  //DRIVER DATA

        binding.rating.setOnRatingBarChangeListener(this);
        binding.send.setOnClickListener(this);
        binding.phone.setOnClickListener(this);

        return view;
    }

    //GET DRIVER DATA
    void getDriverData() {
        Glide.with(getContext()).load(getArguments().getString("img")).into(binding.personalImg); //SET IMAGE

        binding.personalName.setText(delivery.getName()); //SET NAME

        binding.star.setText("" + delivery.getRate()); //GET RATE

        binding.ordersCount.setText("" + delivery.getOrdersCount()); //COUNT OF ORDERS

        binding.rateCounts.setText("" + delivery.getCountOfRate()); //RATE COUNT

        binding.titleName.setText("" + delivery.getName());
    }

    void getData() {

        if (rating == 15) {
            Toasty.warning(getContext(), getString(R.string.you_must_rate));
        } else {
            rateDriverModelView.getData("Bearer " + new saved_data().get_token(getContext()),
                    binding.addComment.getText().toString(), rating, getArguments().getString("id"));

            rateDriverModelView.MutableLiveData.observe(this, new Observer<RateDriverRoot>() {
                @Override
                public void onChanged(RateDriverRoot rateDriverRoot) {
                    new utils().dismiss_dialog(getContext());
                    if (rateDriverRoot.getStatus() != 0) {
                        Toasty.success(getContext(), rateDriverRoot.getMessage(), Toasty.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        rating = (int) ratingBar.getRating();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send) {
            new utils().set_dialog(getContext());
            getData();
        } else if (view.getId() == R.id.phone) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",
                    getArguments().getString("phone"), null)));
        }
    }
}
