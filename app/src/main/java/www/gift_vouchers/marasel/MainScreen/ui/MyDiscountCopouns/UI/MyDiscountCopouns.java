package www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.AddCopounCode.UI.AddCopounCode;
import www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.Model.MyDiscountCodeRoot;
import www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.Model.MyDiscountList;
import www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.Pattern.DiscountPatternAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.MyDiscountCopounsBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDiscountCopouns extends Fragment implements View.OnClickListener {
    MyDiscountCopounsBinding binding;
    MyDiscountCopounsModelView myDiscountCopounsModelView;
    Datum[] data;

    public MyDiscountCopouns() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.my_discount_copouns, container, false);
        View view = binding.getRoot();

        myDiscountCopounsModelView = new MyDiscountCopounsModelView();

        binding.addNewCoupon.setOnClickListener(this);

        getData();

        return view;
    }

    void getData() {
        myDiscountCopounsModelView.getData("Bearer " + new saved_data().get_token(getContext()));
        myDiscountCopounsModelView.MutableLiveData.observe(this, new Observer<MyDiscountCodeRoot>() {
            @Override
            public void onChanged(MyDiscountCodeRoot myDiscountCodeRoot) {
                ArrayList<MyDiscountList> myDiscountLists = new ArrayList<>();
                data = myDiscountCodeRoot.getData();

                for (int index = 0; index < data.length; index++) {
                    myDiscountLists.add(new MyDiscountList("" + data[index].getId(),
                            data[index].getName(), data[index].getLogo()));
                }
                new utils_adapter().Adapter(binding.myCopoundList, new DiscountPatternAdapter(getContext(), myDiscountLists), getContext());

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_new_coupon) {
            new utils().Replace_Fragment(new AddCopounCode(), R.id.frag, getContext());
        }
    }
}
