package www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.DeliveryPlace;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.TimeList;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Pattern.TimeAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.DeliveryTimeBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils_adapter;

public class DeliveryTime extends BottomSheetDialogFragment {
    View view;
    Datum[] data;
    ArrayList<TimeList> TimeList = new ArrayList<>();
    DeliveryTimeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.delivery_time, container, false);
        view = binding.getRoot();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();

    }

    void getData()
    {
        MakeOrderModelView MakeOrderModelView = new MakeOrderModelView();
        MakeOrderModelView.getData("Bearer " + new saved_data().get_token(getContext()));

        MakeOrderModelView.MutableLiveData.observe(this, new Observer<DeliveryPlace>() {
            @Override
            public void onChanged(DeliveryPlace deliveryPlace) {
                setData(deliveryPlace);
            }
        });
    }

    void setData(DeliveryPlace deliveryPlace)
    {
        data = deliveryPlace.getData();
        for(int i = 0 ; i < data.length ; i++)
        {
            TimeList.add(new TimeList(""+ data[i].getId(),"" + data[i].getName()));
        }
        new utils_adapter().Adapter(binding.deliveryList,new TimeAdapter(getContext(),TimeList),getContext());
    }
    
}
