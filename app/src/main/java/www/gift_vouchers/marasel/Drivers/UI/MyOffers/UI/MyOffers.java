package www.gift_vouchers.marasel.Drivers.UI.MyOffers.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.Datum;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.MyOfferList;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model.MyOffersRoot;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.UI.WorkAsStarModelView;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.MyOffersBinding;
import www.gift_vouchers.marasel.local_data.saved_data;

/**
 * A simple {@link Fragment} subclass.
 * * create an instance of this fragment.
 */
public class MyOffers extends Fragment {
    MyOffersBinding binding;
    MyOffersViewModel myOffersViewModel;
    Datum[] data;
    ArrayList<MyOfferList> myOfferLists = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.my_offers, container, false);
        View view = binding.getRoot();

        myOffersViewModel = new MyOffersViewModel();

        getData();

        return view;
    }

    void getData() {
        myOffersViewModel.getData("Bearer " + new saved_data().get_token(getContext()));
        myOffersViewModel.MutableLiveData.observe(this, new Observer<MyOffersRoot>() {
            @Override
            public void onChanged(MyOffersRoot myOffersRoot) {
                data = myOffersRoot.getData();
                for (int index = 0; index < data.length; index++) {
//                    myOfferLists.add(new MyOfferList("" + data[index].getId(), ));
                }
            }
        });
    }

}