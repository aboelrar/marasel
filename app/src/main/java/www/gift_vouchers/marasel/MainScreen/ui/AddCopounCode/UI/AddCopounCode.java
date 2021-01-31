package www.gift_vouchers.marasel.MainScreen.ui.AddCopounCode.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.AddCopounCode.Model.AddCouponCodeRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.AddCopounCodeBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCopounCode extends Fragment implements View.OnClickListener {
    AddCopounCodeBinding binding;
    AddCopounCodeModelView addCopounCodeModelView;

    public AddCopounCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.add_copoun_code, container, false);
        View view = binding.getRoot();

        addCopounCodeModelView = new AddCopounCodeModelView();

        binding.checkCopoun.setOnClickListener(this);

        return view;
    }

    void getData() {
        if (binding.addCopoun.getText().toString().equals("")) {
            Toasty.warning(getContext(), getString(R.string.insert_coupon), Toasty.LENGTH_SHORT).show();
        } else {

            new utils().set_dialog(getContext()); //OPEN PROGRSS DIALOG

            addCopounCodeModelView.getData("Bearer " + new saved_data().get_token(getContext()),
                    binding.addCopoun.getText().toString());

            addCopounCodeModelView.MutableLiveData.observe(this, new Observer<AddCouponCodeRoot>() {
                @Override
                public void onChanged(AddCouponCodeRoot addCouponCodeRoot) {

                    new utils().dismiss_dialog(getContext()); //CLOSE PROGRSS DIALOG

                    if (addCouponCodeRoot.getStatus() == 0) {
                        Toasty.warning(getContext(), addCouponCodeRoot.getMessage(), Toasty.LENGTH_SHORT).show();
                    } else {
                        Toasty.success(getContext(), addCouponCodeRoot.getMessage(), Toasty.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.check_copoun) {
            getData();
        }
    }
}
