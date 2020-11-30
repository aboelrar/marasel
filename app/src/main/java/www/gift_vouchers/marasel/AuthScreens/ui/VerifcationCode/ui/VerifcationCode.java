package www.gift_vouchers.marasel.AuthScreens.ui.VerifcationCode.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifcationCode extends Fragment {

    public VerifcationCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.verifcation_code, container, false);
    }
}
