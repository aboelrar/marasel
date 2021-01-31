package www.gift_vouchers.marasel.MainScreen.ui.ChangePassword.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.ChangePassword.Model.ChangePasswordRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.ChangePasswordBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

import static www.gift_vouchers.marasel.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePassword extends Fragment implements View.OnClickListener {
    ChangePasswordBinding binding;
    ChangePasswordModelView changePasswordModelView;

    public ChangePassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.change_password, container, false);
        View view = binding.getRoot();

        changePasswordModelView = new ChangePasswordModelView();

        binding.confirm.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.confirm) {
            passValidation();
        }
    }

    void getData() {
        new utils().set_dialog(getContext()); //OPEN DIALOG

        changePasswordModelView.getData("Bearer " + new saved_data().get_token(getContext()),
                binding.newPass.getText().toString(), binding.oldPass.getText().toString());

        changePasswordModelView.MutableLiveData.observe(this, new Observer<ChangePasswordRoot>() {
            @Override
            public void onChanged(ChangePasswordRoot changePasswordRoot) {

                new utils().dismiss_dialog(getContext()); //DISMISS DIALOG

                if (changePasswordRoot.getStatus() == 0) {
                    Toasty.warning(getContext(), changePasswordRoot.getMessage(), Toasty.LENGTH_SHORT).show();
                } else {
                    Toasty.success(getContext(), changePasswordRoot.getMessage(), Toasty.LENGTH_SHORT).show();
                }
            }
        });
    }

    //validation text
    void passValidation() {
        if (binding.oldPass.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.oldPass.setError(pass_val);
            yoyo(R.id.old_pass, binding.oldPass);
        } else if (binding.newPass.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.newPass.setError(pass_val);
            yoyo(R.id.new_pass, binding.newPass);
        } else if (!binding.coNewPass.getText().toString().equals(binding.newPass.getText().toString()))  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_d_match);
            binding.coNewPass.setError(pass_val);
            yoyo(R.id.co_new_pass, binding.coNewPass);
        } else {
            getData();
        }
    }
}
