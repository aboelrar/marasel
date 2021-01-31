package www.gift_vouchers.marasel.MainScreen.ui.Settings.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.AuthScreens.ui.auth;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.Datum;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.Delivery;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.Model.DeliveryInfoRoot;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.UI.DeliveryPersonalInfoModelView;
import www.gift_vouchers.marasel.MainScreen.ui.AddCopounCode.UI.AddCopounCode;
import www.gift_vouchers.marasel.MainScreen.ui.MyDiscountCopouns.UI.MyDiscountCopouns;
import www.gift_vouchers.marasel.MainScreen.ui.MySettings.UI.MySettings;
import www.gift_vouchers.marasel.MainScreen.ui.Settings.Model.LogoutRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.Settings.Ui.AboutApp.UI.AboutApp;
import www.gift_vouchers.marasel.Settings.Ui.MaraselWork.UI.WorkWithMarasel;
import www.gift_vouchers.marasel.Settings.Ui.PrivacyPolicy.UI.PrivacyPolicy;
import www.gift_vouchers.marasel.Settings.Ui.TermsAndCondition.UI.TermsAndCondition;
import www.gift_vouchers.marasel.databinding.SettingsBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment implements View.OnClickListener {
    SettingsBinding binding;
    SettingsModelView settingsModelView;
    Datum datum;
    Delivery delivery;

    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater, R.layout.settings, container, false);
        View view = binding.getRoot();

        settingsModelView = new SettingsModelView();

        binding.myCopoun.setOnClickListener(this);
        binding.settings.setOnClickListener(this);
        binding.addCoupon.setOnClickListener(this);
        binding.logout.setOnClickListener(this);
        binding.aboutApp.setOnClickListener(this);
        binding.termOfUse.setOnClickListener(this);
        binding.privacyPolicy.setOnClickListener(this);
        binding.howDoIWorkWithMrasil.setOnClickListener(this);

        getData();

        return view;
    }

    private void getData() {
        settingsModelView.getData("Bearer " + new saved_data().get_token(getContext()));
        settingsModelView.MutableLiveData.observe(this, new Observer<DeliveryInfoRoot>() {
            @Override
            public void onChanged(DeliveryInfoRoot deliveryInfoRoot) {
                binding.progressCircular.setVisibility(View.GONE);
                datum = deliveryInfoRoot.getData();
                delivery = datum.getDelivery();

                Glide.with(getContext()).load(datum.getImage()).into(binding.personalImg); //SET IMAGE

                //SET BALANCE
                if (datum.getBalance().equals("")) {
                    binding.balance.setText(getString(R.string.zero_balance));
                } else {
                    binding.balance.setText(datum.getBalance() + " " + getString(R.string.egp));
                }

                binding.orderNums.setText("" + delivery.getOrdersCount()); //GET ORDERS COUNT

                binding.name.setText(delivery.getName()); //SET NAME

                binding.ratings.setText("" + delivery.getCountOfRate()); //GET RATE

            }
        });
    }


    private void getDataLogout() {
        new utils().set_dialog(getContext()); //OPEN DIALOG
        settingsModelView.getDataLogout("Bearer " + new saved_data().get_token(getContext()));
        settingsModelView.MutableLiveDataLogout.observe(this, new Observer<LogoutRoot>() {
            @Override
            public void onChanged(LogoutRoot logoutRoot) {
                new utils().dismiss_dialog(getContext()); //CLOSE DIALOG
                if (logoutRoot.getStatus() == 0) {
                    Toasty.warning(getContext(), logoutRoot.getMessage(), Toasty.LENGTH_SHORT).show();
                } else {
                    Toasty.success(getContext(), logoutRoot.getMessage(), Toasty.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), auth.class));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.my_copoun) {
            new utils().Replace_Fragment(new MyDiscountCopouns(), R.id.frag, getContext());
        } else if (view.getId() == R.id.settings) {
            new utils().Replace_Fragment(new MySettings(), R.id.frag, getContext());
        } else if (view.getId() == R.id.add_coupon) {
            new utils().Replace_Fragment(new AddCopounCode(), R.id.frag, getContext());
        } else if (view.getId() == R.id.logout) {
            getDataLogout();
        } else if (view.getId() == R.id.about_app) {
            new utils().Replace_Fragment(new AboutApp(), R.id.frag, getContext());
        } else if (view.getId() == R.id.how_do_i_work_with_mrasil) {
            new utils().Replace_Fragment(new WorkWithMarasel(), R.id.frag, getContext());
        } else if (view.getId() == R.id.term_of_use) {
            new utils().Replace_Fragment(new TermsAndCondition(), R.id.frag, getContext());
        } else if (view.getId() == R.id.privacy_policy) {
            new utils().Replace_Fragment(new PrivacyPolicy(), R.id.frag, getContext());
        }
    }
}
