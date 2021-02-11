package www.gift_vouchers.marasel.AuthScreens.ui.SignUp.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.AuthScreens.Model.AuthRoot;
import www.gift_vouchers.marasel.AuthScreens.ui.VerifcationCode.ui.VerifcationCode;
import www.gift_vouchers.marasel.AuthScreens.ui.login.ui.loading;
import www.gift_vouchers.marasel.AuthScreens.ui.login.ui.login;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.SignUpBinding;
import www.gift_vouchers.marasel.utils.UserInformation;
import www.gift_vouchers.marasel.utils.utils;

import static www.gift_vouchers.marasel.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment implements View.OnClickListener, EditText.OnEditorActionListener {
    SignUpBinding binding;
    SignUpModeView signUpModeView;

    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.sign_up, container, false);
        View view = binding.getRoot();

        // CALL MODEL VIEW
        signUpModeView = new SignUpModeView();

        binding.back.setOnClickListener(this);
        binding.regist.setOnClickListener(this);
        binding.password.setOnEditorActionListener(this);

        utils.firebase_token();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            new utils().Replace_Fragment(new login(), R.id.frag, getContext());
        } else if (view.getId() == R.id.regist) {

            signUpValidation();
        }
    }

    //GET DATA
    void getData() {
        //CALL API
        signUpModeView.getData(binding.phone.getText().toString(), binding.email.getText().toString(),
                binding.password.getText().toString(), utils.firebase_token(), "1");

        signUpModeView.MutableLiveData.observe(this, new Observer<AuthRoot>() {
            @Override
            public void onChanged(AuthRoot authRoot) {
                new utils().dismiss_dialog(getContext()); //STOP PROGRESS

                if (authRoot.getStatus() == 0) {
                    Toasty.warning(getContext(), authRoot.getMessage(), Toasty.LENGTH_LONG).show();
                } else {

                    UserInformation.addLocalData(authRoot.getData(), getContext()); //ADD LOCAL DATA
//                    new utils().Replace_Fragment(new VerifcationCode(), R.id.frag, getContext());
                    new loading().dialog(getContext(), R.layout.successful_login, .90, "1"); //OPEN DIALOG


                }

            }
        });
    }


    //SignUp VALIDATION
    void signUpValidation() {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (binding.email.getText().toString().length() < 5 && !binding.email.getText().toString().matches(emailPattern))  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.invalid_email_address);
            binding.email.setError(username_val);
            yoyo(R.id.email, binding.email);

        } else if (binding.phone.getText().toString().length() < 11) {
            String phone_val = getResources().getString(R.string.phone);
            binding.phone.setError(phone_val);
            yoyo(R.id.phone, binding.phone);
        } else if (binding.password.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.password.setError(pass_val);
            yoyo(R.id.password, binding.password);
        } else {

            //PROGRESS BAR
            new utils().set_dialog(getContext());

            getData();

        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            signUpValidation();
            return true;
        }
        return false;
    }
}
