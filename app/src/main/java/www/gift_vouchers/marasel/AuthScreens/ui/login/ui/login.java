package www.gift_vouchers.marasel.AuthScreens.ui.login.ui;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import www.gift_vouchers.marasel.AuthScreens.Model.AuthRoot;
import www.gift_vouchers.marasel.AuthScreens.Model.Datum;
import www.gift_vouchers.marasel.AuthScreens.ui.SignUp.ui.SignUp;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.LoginBinding;
import www.gift_vouchers.marasel.local_data.send_data;
import www.gift_vouchers.marasel.utils.UserInformation;
import www.gift_vouchers.marasel.utils.utils;

import static www.gift_vouchers.marasel.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class login extends Fragment implements View.OnClickListener, EditText.OnEditorActionListener {
    LoginBinding binding;
    LoginModeView LoginModeView;
    Datum datum;

    public login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.login, container, false);
        View view = binding.getRoot();

        LoginModeView = new LoginModeView(); // CALL MODEL VIEW

        binding.login.setOnClickListener(this);
        binding.regist.setOnClickListener(this);
        binding.password.setOnEditorActionListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login) {

            loginValidation();

        } else if (view.getId() == R.id.regist) {

            new utils().Replace_Fragment(new SignUp(), R.id.frag, getContext()); //REPLACE FRAGMENT
        }
    }


    //GET DATA
    void getData() {
        LoginModeView.MutableLiveData.observe(this, new Observer<AuthRoot>() {
            @Override
            public void onChanged(AuthRoot authRoot) {
                new utils().dismiss_dialog(getContext()); //STOP PROGRESS

                if (authRoot.getStatus() == 0)
                {
                    Toasty.warning(getContext(),authRoot.getMessage(),Toasty.LENGTH_LONG).show();
                }
                else {
                    UserInformation.addLocalData(authRoot.getData(),getContext()); //ADD LOCAL DATA
                    new loading().dialog(getContext(), R.layout.successful_login, .90, "1"); //OPEN DIALOG
                }

            }
        });
    }


    //LOGIN VALIDATION
    void loginValidation() {

        if (binding.email.getText().toString().length() < 5)  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.user_val);
            binding.email.setError(username_val);
            yoyo(R.id.email, binding.email);

        }
        else if (binding.password.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.password.setError(pass_val);
            yoyo(R.id.password, binding.password);
        } else {

           //PROGRESS BAR
            new utils().set_dialog(getContext());

            LoginModeView.getData(binding.email.getText().toString()
                    , binding.password.getText().toString(), "0"); //CALL API
    }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            loginValidation();
            return true;
        }
        return false;
    }
}
