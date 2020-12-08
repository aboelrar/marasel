package www.gift_vouchers.marasel.AuthScreens.ui.login.ui;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import www.gift_vouchers.marasel.AuthScreens.Model.AuthRoot;
import www.gift_vouchers.marasel.AuthScreens.Model.Datum;
import www.gift_vouchers.marasel.AuthScreens.ui.SignUp.ui.SignUp;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.LoginBinding;
import www.gift_vouchers.marasel.local_data.send_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class login extends Fragment implements View.OnClickListener {
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

        getData(); //GET DATA FROM SERVER

        return view;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login) {

            //PROGRESS BAR
            new utils().set_dialog(getContext());

            LoginModeView.getData(binding.email.getText().toString()
                    , binding.password.getText().toString(), "0"); //CALL API

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

                addLocalData(authRoot.getData()); //ADD LOCAL DATA

                new loading().dialog(getContext(), R.layout.successful_login, .90, "0"); //OPEN DIALOG


            }
        });
    }

    //ADD DATA TO LOCAL
    void addLocalData(Datum datum)
    {
        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(datum.getName());
        arrayList.add(datum.getEmail());
        arrayList.add(datum.getPhone());
        arrayList.add(datum.getToken());
        arrayList.add(""+ datum.getSocial());


        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(getContext(), arrayList.get(0)); //ADD USER NAME
                send_data.send_email(getContext(), arrayList.get(1)); //ADD Email
                send_data.send_phone(getContext(), arrayList.get(2)); //ADD PHONE
                send_data.send_token(getContext(), arrayList.get(3)); //ADD TOKEN
                send_data.send_type(getContext(), arrayList.get(4)); //ADD TYPE

            }
        });
    }
}
