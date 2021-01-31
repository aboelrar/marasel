package www.gift_vouchers.marasel.MainScreen.ui.MySettings.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.MainScreen.ui.ChangeLanguage.UI.ChangeLanguage;
import www.gift_vouchers.marasel.MainScreen.ui.ChangePassword.UI.ChangePassword;
import www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.UI.PersonalInformation;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.MySettingsBinding;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySettings extends Fragment implements View.OnClickListener {
    MySettingsBinding binding;

    public MySettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.my_settings, container, false);
        View view = binding.getRoot();

        binding.myProfile.setOnClickListener(this);
        binding.changePassword.setOnClickListener(this);
        binding.languages.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.my_profile) {
            new utils().Replace_Fragment(new PersonalInformation(), R.id.frag, getContext());
        } else if (view.getId() == R.id.change_password) {
            new utils().Replace_Fragment(new ChangePassword(), R.id.frag, getContext());
        } else if (view.getId() == R.id.languages) {
            new utils().Replace_Fragment(new ChangeLanguage(), R.id.frag, getContext());
        }
    }
}
