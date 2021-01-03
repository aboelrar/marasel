package www.gift_vouchers.marasel.MainScreen.ui.RateStore.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.UI.myOrder;
import www.gift_vouchers.marasel.MainScreen.ui.RateStore.Model.RateStoreRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.RateStoreBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RateStore extends Fragment implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    RateStoreBinding binding;
    RateStoreModelView rateStoreModelView;
    public RateStore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.rate_store, container, false);
        View view = binding.getRoot();

        binding.myOrders.setOnClickListener(this);
        binding.rating.setOnRatingBarChangeListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        rateStoreModelView = new RateStoreModelView();
        getData();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

        rateStoreModelView.getData("Bearer "+new saved_data().get_token(getContext()),
                getArguments().getString("id"),ratingBar.getNumStars());
    }

    void getData()
    {
        rateStoreModelView.MutableLiveData.observe(this, new Observer<RateStoreRoot>() {
            @Override
            public void onChanged(RateStoreRoot rateStoreRoot) {
                Toasty.success(getContext(),""+rateStoreRoot.getMessage(),Toasty.LENGTH_LONG).show();
                
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.myOrders)
        {
            new utils().Replace_Fragment(new myOrder(),R.id.frag,getContext());
        }
    }
}
