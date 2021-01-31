package www.gift_vouchers.marasel.AuthScreens.ui.Notifcations.Ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notifcations extends Fragment {

    public Notifcations() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.notifcations, container, false);
    }
}
