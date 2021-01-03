package www.gift_vouchers.marasel.MainScreen.ui.MyOrder.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class myOrder extends Fragment {

    public myOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_order, container, false);
    }
}
