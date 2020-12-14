package www.gift_vouchers.marasel.MainScreen.ui.home.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.Cat;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.MaraselServiceList;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.NearestStoresList;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.Store;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot;
import www.gift_vouchers.marasel.MainScreen.ui.home.pattern.MaraselServiceAdapter;
import www.gift_vouchers.marasel.MainScreen.ui.home.pattern.NearestStoresAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.HomeBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {
 HomeBinding binding;
 HomeModeView homeModeView = new HomeModeView();
 Datum datum;
 Store[] stores;
 ArrayList<NearestStoresList> NearestStoresList = new ArrayList<>();
 Cat[] cat;
 ArrayList<MaraselServiceList> MaraselServiceList = new ArrayList<>();


    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.home, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    void getData()
    {
        homeModeView.getData(new saved_data().get_token(getContext()));

        homeModeView.MutableLiveData.observe(this, new Observer<homeRoot>() {
            @Override
            public void onChanged(homeRoot homeRoot) {
                datum = homeRoot.getData(); // Data
                stores = datum.getStores(); // stores
                cat = datum.getCats(); // Cats

                //add Data TO store
                for(int i = 0; i<stores.length;i++)
                {
                    NearestStoresList.add(new NearestStoresList(""+stores[i].getId(), stores[i].getIcon(),
                            stores[i].getName(),stores[i].getCat(),stores[i].getDistance(),
                            ""+stores[i].getRate(),""+stores[i].getFreeDelivery()));
                }

                new utils_adapter().Horozintal(binding.nearestStoreList,new NearestStoresAdapter(getContext(),NearestStoresList),getContext());

                //add Data TO Cats
                for(int i = 0; i<cat.length;i++)
                {
                    MaraselServiceList.add(new MaraselServiceList(""+cat[i].getId(),
                            cat[i].getIcon(),cat[i].getName()));
                }

                new utils_adapter().griddAdapters(binding.maraselServiceList,
                        new MaraselServiceAdapter(getContext(),MaraselServiceList),getContext(),3);

            }
        });
    }

}
