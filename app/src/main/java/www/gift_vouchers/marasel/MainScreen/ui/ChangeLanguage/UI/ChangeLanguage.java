package www.gift_vouchers.marasel.MainScreen.ui.ChangeLanguage.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeLanguage extends Fragment {

    public ChangeLanguage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.change_language, container, false);
    }
}
